package com.vamsi.springbatch.processor;

import com.vamsi.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {


    private static final Map <String, String> DEP_NAMES = new HashMap<>();

    public Processor() {
        DEP_NAMES.put("001", "Technology");
        DEP_NAMES.put("002", "Math");
        DEP_NAMES.put("003", "Statistics");
    }

    @Override
    public User process(User user) throws Exception {
        user.setDept(DEP_NAMES.get(user.getDept()));
        return user;
    }
}
