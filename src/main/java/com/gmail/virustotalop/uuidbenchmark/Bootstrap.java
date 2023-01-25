package com.gmail.virustotalop.uuidbenchmark;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.function.Supplier;

public class Bootstrap {

    private static final int ITERATIONS = 100_000_000;

    public static void main(String[] args) {
        String name = "MinecraftName123";
        UUID uuid = UUID.randomUUID();
        //Warmup
        run(String.class, () -> name.hashCode(),  false);
        run(UUID.class, () -> uuid.hashCode(), false);
        //Benchmark
        run(String.class, () -> name.hashCode(),  true);
        run(UUID.class, () -> uuid.hashCode(), true);
    }

    private static <T> void run(Class<?> clazz, Supplier<T> supplier, boolean benchmark) {
        long start = System.currentTimeMillis();
        Collection<T> col = new HashSet<>();
        for (int i = 0; i < ITERATIONS; i++) {
            col.add(supplier.get());
        }
        if (benchmark) {
            long diff = System.currentTimeMillis() - start;
            System.out.println(String
                    .format(
                            "Benchmarked %s class, took %d ms to iterate %d times",
                            clazz.getSimpleName(),
                            diff,
                            ITERATIONS
                    )
            );
        }
    }
}
