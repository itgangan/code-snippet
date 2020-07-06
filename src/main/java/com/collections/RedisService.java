/**
 * Copyright (c) 2020,TravelSky.
 * All Rights Reserved.
 * TravelSky CONFIDENTIAL
 */

package com.collections;

import redis.clients.jedis.Jedis;

/**
 * The type of code-snippet.
 *
 * <p>
 * .
 *
 * @author ganxiangyong
 */
public class RedisService {

    private static final RedisService instance = new RedisService();

    private RedisService() {

    }

    public static RedisService getInstance() {
        return instance;
    }

    public Jedis getConnection() {
        return null;
    }
}
