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

package org.apache.shardingsphere.db.protocol.mysql.packet.command.query.text.fieldlist;

import lombok.Getter;
import org.apache.shardingsphere.db.protocol.mysql.packet.command.MySQLCommandPacket;
import org.apache.shardingsphere.db.protocol.mysql.packet.command.MySQLCommandPacketType;
import org.apache.shardingsphere.db.protocol.mysql.payload.MySQLPacketPayload;

/**
 * COM_FIELD_LIST command packet for MySQL.
 * 
 * @see <a href="https://dev.mysql.com/doc/dev/mysql-server/latest/page_protocol_com_field_list.html">COM_FIELD_LIST</a>
 */
@Getter
public final class MySQLComFieldListPacket extends MySQLCommandPacket {
    
    private final String table;
    
    private final String fieldWildcard;
    
    public MySQLComFieldListPacket(final MySQLPacketPayload payload) {
        super(MySQLCommandPacketType.COM_FIELD_LIST);
        table = payload.readStringNul();
        fieldWildcard = payload.readStringEOF();
    }
    
    @Override
    public void doWrite(final MySQLPacketPayload payload) {
        payload.writeStringNul(table);
        payload.writeStringEOF(fieldWildcard);
    }
}
