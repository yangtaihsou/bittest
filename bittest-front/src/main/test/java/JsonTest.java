import com.bittest.platform.bg.common.utils.ParameterReplaceUtils;

public class JsonTest {
    public static void main(String[] args){
        String jsonString = "{\"info\":[{\"goodsId\":\"12345\",\"goodsq\":\"10\"},{\"goodsId\":\"5678\",\"goodsq\":\"20\"}]}";
        String key = "goodsId\":";
        int index =  jsonString.indexOf(key);

        int firstPoint = index + key.length();
        String afterString = jsonString.substring(firstPoint);
        int afterIndex = afterString.indexOf(",\"");
        String value = jsonString.substring(firstPoint,firstPoint+afterIndex);

       int  valueBefore = value.indexOf("\"");

       if(valueBefore==0){
           int  valueAfter = value.lastIndexOf("\"");
           value = value.substring(valueBefore+1,valueAfter);

       }else{
           valueBefore = value.indexOf("\'");
           if(valueBefore==0) {
               int  valueAfter = value.lastIndexOf("\'");
               value = value.substring(valueBefore + 1, valueAfter);

           }
       }
        System.out.println(value);

       System.out.print(ParameterReplaceUtils.getParaValue(jsonString,"goodsId"));
    }
}
