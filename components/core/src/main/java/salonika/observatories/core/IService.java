package salonika.observatories.core;

import salonika.observatories.core.bean.Beam;

/**
 * @Author: penghaoyang
 * @Date: 2019/6/27 14:33
 * @Description: IService
 */
public interface IService {

    Beam callPrisma(Beam beam);

    Beam callProbe(Beam beam);

    Beam callPylon(Beam beam);

    Beam callSentry(Beam beam);
}
