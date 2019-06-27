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
public class PrismaFeignConfiguration {

    @FeignClient(value = "prisma", fallbackFactory = PrismaFallbackFactory.class)
    public interface IPrismaClient {
        @PostMapping("refraction/v1")
        Beam callPrisma(@RequestBody Beam beam);
    }

    public class PrismaFallbackFactory implements FallbackFactory<IPrismaClient> {

        @Override
        public IPrismaClient create(Throwable throwable) {
            return new IPrismaClient() {
                @Override
                public Beam callPrisma(Beam beam) {
                    return beam;
                }
            };
        }
    }

}
