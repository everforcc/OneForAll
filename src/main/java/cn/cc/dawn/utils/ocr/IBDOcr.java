package cn.cc.dawn.utils.ocr;

public interface IBDOcr {

    /**
     * ocr识别文字,此接口只处理一个文字
     * 识别前需要校验 数据 是否存在，
     * 识别 后 需要保存数据
     * @param path
     * @return
     */
    String charOcr(String path);

}
