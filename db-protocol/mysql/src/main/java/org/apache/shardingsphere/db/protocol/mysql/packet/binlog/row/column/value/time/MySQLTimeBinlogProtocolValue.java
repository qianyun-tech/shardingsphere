/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.db.protocol.mysql.packet.binlog.row.column.value.time;

import org.apache.shardingsphere.db.protocol.mysql.packet.binlog.row.column.MySQLBinlogColumnDef;
import org.apache.shardingsphere.db.protocol.mysql.packet.binlog.row.column.value.MySQLBinlogProtocolValue;
import org.apache.shardingsphere.db.protocol.mysql.payload.MySQLPacketPayload;

import java.io.Serializable;

/**
 * TIME type value of MySQL binlog protocol.
 * Stored as a 3 byte value with the values stored as multiples of 100. This means that the stored value is in the format HHMMSS
 * <p>
 *     TIME type applied before MySQL 5.6.4.
 * </p>
 *
 * @see <a href="https://dev.mysql.com/doc/dev/mysql-server/latest/field__types_8h.html">field type</a>
 */
public final class MySQLTimeBinlogProtocolValue implements MySQLBinlogProtocolValue {
    
    @Override
    public Serializable read(final MySQLBinlogColumnDef columnDef, final MySQLPacketPayload payload) {
        int time = payload.getByteBuf().readUnsignedMediumLE();
        if (0 == time) {
            return MySQLTimeValueUtils.ZERO_OF_TIME;
        }
        int minuteSecond = Math.abs(time) % 10000;
        return String.format("%02d:%02d:%02d", time / 10000, minuteSecond / 100, minuteSecond % 100);
    }
}
