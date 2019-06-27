package salonika.observatories.core.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import salonika.observatories.core.bean.Beam;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/27 18:08
 * @Description: IFeignClientConfiguration
 */
@Configuration
public class SentryFeignConfiguration {

    @FeignClient(value = "sentry", fallbackFactory = SentryFallbackFactory.class)
    public interface ISentryClient {
        @PostMapping("checker/v1")
        Beam callSentry(@RequestBody Beam beam);
    }

    public class SentryFallbackFactory implements FallbackFactory<ISentryClient>{
        @Override
        public ISentryClient create(Throwable throwable) {
            return new ISentryClient() {
                @Override
                public Beam callSentry(Beam beam) {
                    return beam;
                }
            };
        }
    }

}
