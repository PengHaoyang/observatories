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
public class ProbeFeignConfiguration {

    @FeignClient(value = "probe", fallbackFactory = ProbeFallbackFactory.class)
    public interface IProbeClient {
        @PostMapping("disposal/v1")
        Beam callProbe(@RequestBody Beam beam);
    }

    public class ProbeFallbackFactory implements FallbackFactory<IProbeClient> {

        @Override
        public IProbeClient create(Throwable cause) {
            return new IProbeClient() {
                @Override
                public Beam callProbe(Beam beam) {
                    return beam;
                }
            };
        }
    }

}
