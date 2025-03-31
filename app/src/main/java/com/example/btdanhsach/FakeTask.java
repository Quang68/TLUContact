package com.example.btdanhsach;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class FakeTask<T> extends Task<T> {
    private final T result;
    private final Exception exception;

    // Constructor cho trường hợp thành công
    public FakeTask(T result) {
        this.result = result;
        this.exception = null;
    }

    // Constructor cho trường hợp thất bại
    public FakeTask(Exception exception) {
        this.result = null;
        this.exception = exception;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public boolean isSuccessful() {
        return exception == null;
    }

    @Override
    public T getResult() {
        if (exception != null) {
            throw new RuntimeException(exception);
        }
        return result;
    }

    @Override
    public <X extends Throwable> T getResult(@NonNull Class<X> aClass) throws X {
        if (exception != null) {
            throw (X) exception;
        }
        return result;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public Task<T> addOnCompleteListener(@NonNull OnCompleteListener<T> listener) {
        listener.onComplete(this);
        return this;
    }

    @Override
    public Task<T> addOnSuccessListener(@NonNull OnSuccessListener<? super T> listener) {
        if (isSuccessful() && result != null) {
            listener.onSuccess(result);
        }
        return this;
    }

    @Override
    public Task<T> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super T> listener) {
        return addOnSuccessListener(listener);
    }

    @Override
    public Task<T> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super T> listener) {
        executor.execute(() -> addOnSuccessListener(listener));
        return this;
    }

    @Override
    public Task<T> addOnFailureListener(@NonNull OnFailureListener listener) {
        if (!isSuccessful() && exception != null) {
            listener.onFailure(exception);
        }
        return this;
    }

    @Override
    public Task<T> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener listener) {
        return addOnFailureListener(listener);
    }

    @Override
    public Task<T> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener listener) {
        executor.execute(() -> addOnFailureListener(listener));
        return this;
    }
}
