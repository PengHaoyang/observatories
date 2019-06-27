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
public class PylonFeignConfiguration {

    @FeignClient(value = "pylon", fallbackFactory = PylonFallbackFactory.class)
    public interface IPylonClient {
        @PostMapping("reinforcement/v1")
        Beam callPylon(@RequestBody Beam beam);
    }

    public class PylonFallbackFactory implements FallbackFactory<IPylonClient> {
        @Override
        public IPylonClient create(Throwable cause) {
            return new IPylonClient() {
                @Override
                public Beam callPylon(Beam beam) {
                    return beam;
                }
            };
        }
    }

}
