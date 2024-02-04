package com.karumi.dexter;

interface Thread {
    void execute(Runnable runnable);

    void loop();
}
