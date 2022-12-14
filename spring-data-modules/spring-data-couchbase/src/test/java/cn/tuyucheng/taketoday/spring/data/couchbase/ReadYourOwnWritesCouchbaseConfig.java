package cn.tuyucheng.taketoday.spring.data.couchbase;

import org.springframework.data.couchbase.core.query.Consistency;

public class ReadYourOwnWritesCouchbaseConfig extends MyCouchbaseConfig {

    @Override
    public Consistency getDefaultConsistency() {
        return Consistency.READ_YOUR_OWN_WRITES;
    }
}
