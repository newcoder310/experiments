package com.etcd.etcdtest.configuration;

import com.coreos.jetcd.Client;
import com.coreos.jetcd.KV;
import com.coreos.jetcd.Lease;
import com.coreos.jetcd.common.exception.EtcdException;
import com.coreos.jetcd.data.ByteSequence;
import com.coreos.jetcd.data.KeyValue;
import com.coreos.jetcd.kv.GetResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class EtcdTestConfiguration {

    public static void test(String servername) throws EtcdException, ExecutionException, InterruptedException {
        System.out.println("The server name is" +servername);
        // create client
        Client client = Client.builder().endpoints("http://localhost:2379").build();
        KV kvClient = client.getKVClient();

        ByteSequence key = ByteSequence.fromBytes("leader".getBytes());
        ByteSequence val = ByteSequence.fromBytes(servername.getBytes());
        try {

// put the key-value
            kvClient.put(key, val);


        } catch (Exception e) {
            System.out.println("The usual exception");
        } finally {
// get the CompletableFuture
            CompletableFuture<GetResponse> getFuture = kvClient.get(key);

// get the value from CompletableFuture
            GetResponse response = getFuture.get();
            List<KeyValue> kvs = response.getKvs();

            System.out.println("The leader is "+kvs.get(0).getValue());
        }

    }
}
