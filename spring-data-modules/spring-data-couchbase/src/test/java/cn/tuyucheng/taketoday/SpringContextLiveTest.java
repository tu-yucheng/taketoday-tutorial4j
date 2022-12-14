package cn.tuyucheng.taketoday;

import cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketCouchbaseConfig;
import cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketIntegrationTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * This LiveTest requires:
 * <p>
 * 1- Couchbase instance running (e.g. with `docker run -d --name db -p 8091-8096:8091-8096 -p 11210-11211:11210-11211 couchbase`)
 * <p>
 * <p>
 * 2- Couchbase configured with (we can use the console in localhost:8091):
 * <p>
 * 2.1- Buckets: named 'baeldung' and 'baeldung2'
 * <p>
 * 2.2- Security: users 'baeldung' and 'baeldung2'. Note: in newer versions an empty password is not allowed, then we have to change the passwords in the project)
 * <p>
 * 2.3- Spacial View: Add new spacial view (in Index tab) in document 'campus_spatial', view 'byLocation' with the following function:
 * {@code
 * function (doc) {
 * if (doc.location &&
 * doc._class == "com.baeldung.spring.data.couchbase.model.Campus") {
 * emit([doc.location.x, doc.location.y], null);
 * }
 * }}
 * <p>
 * 2.4- MapReduce Views: Add new views in document 'campus':
 * 2.4.1- view 'all' with function:
 * {@code
 * function (doc, meta) {
 * if(doc._class == "com.baeldung.spring.data.couchbase.model.Campus") {
 * emit(meta.id, null);
 * }
 * }}
 * <p>
 * 2.4.2- view 'byName' with function:
 * {@code
 * function (doc, meta) {
 * if(doc._class == "com.baeldung.spring.data.couchbase.model.Campus" &&
 * doc.name) {
 * emit(doc.name, null);
 * }
 * }}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MultiBucketCouchbaseConfig.class, MultiBucketIntegrationTestConfig.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class SpringContextLiveTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}
