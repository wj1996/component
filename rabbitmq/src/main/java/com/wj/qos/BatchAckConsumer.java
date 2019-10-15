package com.wj.qos;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class BatchAckConsumer extends DefaultConsumer {


    private int messageCount = 0;
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public BatchAckConsumer(Channel channel) {
        super(channel);
        System.out.println("批量消费者启动");
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body,"utf-8");
        System.out.println("批量消费者 received[" + envelope.getRoutingKey() + "]" + msg);
        messageCount++;
        if (messageCount % 50 == 0) {
            this.getChannel().basicAck(envelope.getDeliveryTag(),true);
            System.out.println("批量消费者进行消息的确认...............");
        }

        if ("stop".equals(msg)) {
            this.getChannel().basicAck(envelope.getDeliveryTag(),true);
            System.out.println("批量消费者进行最后部分业务消息的确认");
        }
    }
}
