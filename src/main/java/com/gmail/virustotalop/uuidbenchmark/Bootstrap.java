package com.gmail.virustotalop.uuidbenchmark;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public class Bootstrap {

    private static final int ITERATIONS = 100_000_000;

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        Player player = new Player(uuid);
        //Warmup
        run(UUID.class, uuid, UUID::randomUUID, false);
        run(Player.class, player, () -> new Player(UUID.randomUUID()), false);
        //Benchmark
        run(UUID.class, uuid, UUID::randomUUID, true);
        run(Player.class, player, () -> new Player(UUID.randomUUID()), true);
    }

    private static <T> void run(Class<?> clazz, T obj, Supplier<T> random, boolean benchmark) {
        Set<T> set = new HashSet<>(100);
        set.add(obj);
        for (int i = 0; i < 20; i++) {
            set.add(random.get());
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < ITERATIONS; i++) {
            set.contains(obj);
        }
        long diff = System.currentTimeMillis() - start;
        if (benchmark) {
            System.out.println(String
                    .format(
                            "Benchmarked %s class, took %d ms to iterate %d times",
                            clazz.getSimpleName(),
                            diff,
                            ITERATIONS
                    )
            );
        } else {
            System.out.println(String
                    .format(
                            "Warmup %s class, took %d ms to iterate %d times",
                            clazz.getSimpleName(),
                            diff,
                            ITERATIONS
                    )
            );
        }
    }
}
