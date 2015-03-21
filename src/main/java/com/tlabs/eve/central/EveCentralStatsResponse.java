package com.tlabs.eve.central;

/*
 * #%L
 * This source code is part of the Evanova Android application:
 * https://play.google.com/store/apps/details?id=com.tlabs.android.evanova
 * %%
 * Copyright (C) 2010 - 2012 Evanova (Traquenard Labs)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EveCentralStatsResponse extends EveCentralResponse {

    private Map<Long, List<EveCentralPrice>> marketPrices = new HashMap<>();
    private Map<Long, List<EveCentralPrice>> buyOrders = new HashMap<>();
    private Map<Long, List<EveCentralPrice>> sellOrders = new HashMap<>();

    public final Map<Long, List<EveCentralPrice>> getMarketPrices() {
        return marketPrices;
    }

    public final Map<Long, List<EveCentralPrice>> getBuyPrices() {
        return buyOrders;
    }

    public final Map<Long, List<EveCentralPrice>> getSellPrices() {
        return sellOrders;
    }

    public void add(EveCentralPrice p) {
        switch (p.getType()) {
        case EveCentralPrice.MARKET:
            add(this.marketPrices, p);
            break;
        case EveCentralPrice.BUY:
            add(this.buyOrders, p);
            break;
        case EveCentralPrice.SELL:
            add(this.sellOrders, p);
            break;
        default:
            throw new IllegalArgumentException("Invalid EveCentralPrice.type " + p.getType());
        }
    }

    private static void add(Map<Long, List<EveCentralPrice>> prices, EveCentralPrice p) {
        List<EveCentralPrice> l = prices.get(p.getID());
        if (null == l) {
            l = new LinkedList<>();
            prices.put(p.getID(), l);
        }
        l.add(p);
    }
}
