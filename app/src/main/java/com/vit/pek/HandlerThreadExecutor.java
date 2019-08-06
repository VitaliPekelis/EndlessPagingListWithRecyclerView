package com.vit.pek;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

class HandlerThreadExecutor implements Executor {

    private final Handler handler;

    public HandlerThreadExecutor(Handler optionalHandler) {
        handler = optionalHandler != null ? optionalHandler : new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
