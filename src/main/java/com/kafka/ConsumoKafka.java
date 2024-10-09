package com.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumoKafka {
    public static void main(String[] args) {

        // Envia os parametros necessários, mesmas informações enviadas por cmd
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo1");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);) {
            // Assiste o tópico chamado testeJava
            consumer.subscribe(Arrays.asList("testeJava"));

            while (true) {
                // Aguardar pela chegada das mensagens
                ConsumerRecords<String, String> mensagensRecebidas = consumer.poll(Duration.ofSeconds(1));

                // Exibe tudo que recebeu
                for (ConsumerRecord<String, String> consumerRecord : mensagensRecebidas) {
                    System.out.println(consumerRecord.value());
                }

            }
        }
    }
}
