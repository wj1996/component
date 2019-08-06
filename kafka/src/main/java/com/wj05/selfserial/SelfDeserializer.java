package com.wj05.selfserial;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 一般不会使用自定义的序列化
 *  使用第三方的
 *      messagepack
 *      Kyro
 *      Protobuf
 *      Avro
 */
public class SelfDeserializer implements Deserializer<DemoUser> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public DemoUser deserialize(String topic, byte[] data) {
        try {
            if (null == data) {
                return null;
            }
            if (data.length < 0) {

            }
            ByteBuffer buffer = ByteBuffer.wrap(data);
            int id;
            String name;
            int nameSize;
            id = buffer.getInt();
            nameSize = buffer.getInt();
            byte[] nameByte = new byte[nameSize];
            buffer.get(nameByte);
            name = new String(nameByte,"utf-8");
            return new DemoUser(id,name);
        } catch (Exception e) {

        }
        return null;
    }

    public void close() {

    }
}
