package com.wj05.selfserial;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class SelfSerializer implements Serializer<DemoUser> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public byte[] serialize(String topic, DemoUser data) {
        try {
            byte[] name;
            int nameSize;
            if (null == data) {
                return null;
            }
            if (data.getName() != null) {
                name = data.getName().getBytes("utf-8");
                nameSize = name.length;
            } else {
                name = new byte[0];
                nameSize = 0;
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + nameSize);
            buffer.putInt(data.getId());
            buffer.putInt(nameSize);
            buffer.put(name);
            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException("Error serialize DemoUser:" + e);
        }
    }

    public void close() {

    }
}
