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

package org.apache.shardingsphere.db.protocol.postgresql.packet.command.query;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.db.protocol.postgresql.packet.identifier.PostgreSQLIdentifierPacket;
import org.apache.shardingsphere.db.protocol.postgresql.packet.identifier.PostgreSQLIdentifierTag;
import org.apache.shardingsphere.db.protocol.postgresql.packet.identifier.PostgreSQLMessagePacketType;
import org.apache.shardingsphere.db.protocol.postgresql.payload.PostgreSQLPacketPayload;

import java.util.Collection;

/**
 * Row description packet for PostgreSQL.
 */
@RequiredArgsConstructor
public final class PostgreSQLRowDescriptionPacket extends PostgreSQLIdentifierPacket {
    
    private final Collection<PostgreSQLColumnDescription> columnDescriptions;
    
    @Override
    protected void write(final PostgreSQLPacketPayload payload) {
        payload.writeInt2(columnDescriptions.size());
        for (PostgreSQLColumnDescription each : columnDescriptions) {
            payload.writeStringNul(each.getColumnName());
            payload.writeInt4(each.getTableOID());
            payload.writeInt2(each.getColumnIndex());
            payload.writeInt4(each.getTypeOID());
            payload.writeInt2(each.getColumnLength());
            payload.writeInt4(each.getTypeModifier());
            payload.writeInt2(each.getDataFormat());
        }
    }
    
    @Override
    public PostgreSQLIdentifierTag getIdentifier() {
        return PostgreSQLMessagePacketType.ROW_DESCRIPTION;
    }
}
