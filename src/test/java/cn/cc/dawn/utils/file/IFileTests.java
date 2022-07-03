package cn.cc.dawn.utils.file;

import cn.cc.dawn.common.bddisk.upload.vo.BDFileVo;
import cn.cc.dawn.utils.file.impl.FileApacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
public class IFileTests {

    private static IFile apacheiFile = new FileApacheUtils();

    @Test
    public void size(){
        try {
            String path = "E:\\filesystem\\project\\OneForAll\\webapi\\bddisk\\file\\诺艾尔.jpg";
            String delPath = "E:\\filesystem\\project\\OneForAll\\webapi\\bddisk\\file\\temp\\诺艾尔.jpg_1";
            //System.out.println(apacheiFile.fileSizeByte(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void blockList(){
        String path = "E:\\filesystem\\project\\OneForAll\\webapi\\bddisk\\file\\OfficeSetup.exe";
        List<String> stringList = apacheiFile.block_list(path,true).getBlock_list();
        stringList.forEach(System.out::println);
    }

    @org.junit.Test
    public void param(){
        BDFileVo bdFileVo = new BDFileVo();
        bdFileVo.setSize(10);
        System.out.println(bdFileVo.getSize());
        param(bdFileVo);
        System.out.println(bdFileVo.getSize());
    }

    public void param(BDFileVo bdFileVo){
        bdFileVo.setSize(100);
    }


    @org.junit.Test
    public void md5List(){
        try {
            int targetLength = 1024 * 1024 * 4;
            int totalLength = 1024 * 1024 * 10;
            byte b = 1;
            byte[] bytes = new byte[totalLength];
            for(int i=0; i<10; i++){
                bytes[i] = b++;
            }

            System.out.println("原数组: " + Arrays.toString(bytes));
            System.out.println("原数组长度: " + totalLength);

            List<byte[]> byteList =  new ArrayList<>();

            int startIndex = 0;

            int count = totalLength/targetLength;
            int residue = totalLength%targetLength;
            System.out.println("count: " + count);
//            do{
//                System.out.println("startIndex: " + startIndex);
//                byte[] target = new byte[targetLength];
//                System.arraycopy(bytes,startIndex,target,0,targetLength);
//                byteList.add(target);
//                startIndex+=targetLength;
//            }while (startIndex<totalLength);

            for(int i=0; i<count; i++){
                byte[] target = new byte[targetLength];
                System.arraycopy(bytes,i*targetLength,target,0,targetLength);
                byteList.add(target);
            }
            if(residue>0){
                byte[] target = new byte[residue];
                System.arraycopy(bytes,count*targetLength,target,0,residue);
                byteList.add(target);
            }


            for(byte[] bytes1: byteList){
                System.out.println("新数组: " + Arrays.toString(bytes1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void write(){
        try {
            apacheiFile.write(FilePath.build().ofPath("/test/novel").ofFileName("test.txt").path(),"123\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read(){
        try {
            log.info(apacheiFile.readTXT(FilePath.build().ofPath("/test/novel").ofFileName("test.txt").path()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
