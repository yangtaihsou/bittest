

import com.bittest.platform.bg.common.utils.EncryptUtil
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.cyberneko.html.parsers.SAXParser

import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.TEXT

def loginPage() {
    def http = new HTTPBuilder('https://passport.bittest123.com/login?locale=zh&service=https%3A%2F%2Fshop-repair-beta.bittest123.com.cn%2Fuser%2FafterSale%2FrepairHeaderList')
    http.request(GET, TEXT) { req ->
        //uri.path = '/mail/help/tasks/'
        //请求成功
        response.success = { resp, reader ->
            List<String> cookies = [];
            resp.getHeaders('Set-Cookie').each {
                String cookie = it.value.split(';')[0]
               println 'loginPage cookie==='+cookie
                cookies.add(cookie)
            }
            //println 'cookie==='+cookies
            def slurper = new XmlSlurper(new SAXParser())
            def htmlParser = slurper.parse(reader)
            def postdata = ''
            htmlParser.'**'.findAll { it.@name == '_xsrf'||it.@name == 'lt'||it.@name == 'public'||it.@name == 'service'  }.each {
                postdata = postdata + '&'+it.@name+'='+it.@value
            }
           // println postdata;

            String publicKey = ''
            htmlParser.'**'.findAll { it.@name == 'public'}.each {
                 //println  it+'--------'+it.@value
                publicKey =  it.@value
            }
            publicKey = publicKey.replace('-----BEGIN PUBLIC KEY-----' ,'')
            publicKey = publicKey.replace('-----END PUBLIC KEY-----','')
            publicKey = publicKey.replace('\n','')
            publicKey = publicKey.replace('\r','')
            //println publicKey;
            String encrptPasswd = new String(EncryptUtil.encryptRSA('crystal08', publicKey));
            encrptPasswd = URLEncoder.encode(encrptPasswd)
          //  println 'encrptPasswd--------='+encrptPasswd;
            //println '---------------\n';
            postdata = postdata +'&username=mamingfeng007@outlook.com&password='+encrptPasswd;
            //println postdata;
            loginRequest(postdata,cookies);

        }


    }
}

def loginRequest(postData,cookies){
    def http = new HTTPBuilder('https://passport.bittest123.com/login')
    http.request(POST, ContentType.URLENC) {
        cookies = cookies.join(';')
        headers.'cookie' = cookies
       // println cookies
        body = postData
       // println postData
        response.success = { resp, reader ->
            //println reader

            //println resp.responseBase.statusLine
            String location = resp.getHeaders('Location')[0].value
             def locationValue = location.split('\\?')[1]
            //println locationValue
            resp.headers.each{language->
                //println language
            }
            List<String> logincookies = [];
            resp.getHeaders('Set-Cookie').each {
                String cookie = it.value.split(';')[0]
                 //println 'loginRequest cookie==='+cookie
                logincookies.add(cookie)
            }
            cookies = cookies + ";"+locationValue;// +";"+logincookies.join(';')
            //locationRequest(location,cookies);
            loginCAS(cookies)
        }
    }
}
def locationRequest(locationUrl,cookies){
    def http = new HTTPBuilder(locationUrl)
    //def http = new HTTPBuilder('https://passport.bittest123.com/login')
    List<String> logincookies = [];
    http.request(GET, ContentType.TEXT) {
        headers.'cookie' = cookies
        //println cookies
        // println postData
        response.success = { resp, reader ->
            //println reader.text
            resp.getHeaders('Set-Cookie').each {
                String cookie = it.value.split(';')[0]
                //println 'locationRequest cookie==='+cookie
                logincookies.add(cookie)
            }
            //cookies = cookies +";"+logincookies.join(';')
            loginCAS(cookies)
            //getShopcartList(cookies)
        }
    }
}
def loginCAS(cookies){
    def http = new HTTPBuilder('https://shop-base-beta.bittest123.com/api/casLogin')
    //def http = new HTTPBuilder('https://passport.bittest123.com/login')
    http.request(GET, ContentType.ANY) {
        println cookies
        headers.'cookie' = cookies
        headers.'referer' = 'https://shop-repair-beta.bittest123.com.cn/user/afterSale/repairHeaderList'
        // println postData

        List<String> logincookies = [];
        response.success = { resp, reader ->
            println reader.text
            resp.getHeaders('Set-Cookie').each {
                String cookie = it.value.split(';')[0]
                  println 'cookie==='+cookie
                logincookies.add(cookie)
            }
            cookies = cookies +";"+logincookies.join(';')
            getShopcartList(cookies)
        }
        //401
        response.'401' = { resp ->
            println 'loginCAS Not found'
        }

    }
}

def getShopcartList(cookies){

    def http = new HTTPBuilder('https://shop-order-beta.bittest123.com/api/shopcart/list')
    //def http = new HTTPBuilder('https://passport.bittest123.com/login?locale=zh&service=https%3A%2F%2Fshop-repair-beta.bittest123.com.cn%2Fuser%2FafterSale%2FrepairHeaderList')
    http.request(GET, ContentType.JSON) {
        //cookies = cookies.join(';')
        headers.'cookie' = cookies
        headers.'http.protocol.cookie-policy' = 'compatibility'
        println cookies
        //请求成功
        response.success = { resp, reader ->
            println resp
            println reader
        }
        //404
        response.'401' = { resp ->
            println 'getShopcartList Not found'
        }

        // 401
        http.handler.'401' = { resp ->
            println "Access denied"
        }

        //其他错误，不实现则采用缺省的：抛出异常。
        http.handler.failure = { resp ->
            println "Unexpected failure: ${resp.statusLine}"
        }
    }
}


static main(def args) {
    //getHttp()
    loginPage()
    //getShopcartList()
    def cookies = ''
    //loginCAS(cookies)
}

def getHttp() {
    def slurper = new XmlSlurper(new SAXParser())
    def htmlParser = slurper.parse('https://passport.bittest123.com/login?locale=zh&service=https%3A%2F%2Fshop-repair-beta.bittest123.com.cn%2Fuser%2FafterSale%2FrepairHeaderList')
    def postdata = ''
    htmlParser.'**'.findAll { it.@name == '_xsrf'||it.@name == 'lt'||it.@name == 'public'||it.@name == 'service'  }.each {
        postdata = postdata + '&'+it.@name+'='+it.@value
    }
    //println postdata;

    String publicKey = ''
    htmlParser.'**'.findAll { it.@name == 'public'}.each {
        // println  it+'--------'+it.@value
        publicKey =  it.@value
    }
    publicKey = publicKey.replace('-----BEGIN PUBLIC KEY-----' ,'')
    publicKey = publicKey.replace('-----END PUBLIC KEY-----','')
    publicKey = publicKey.replace('\n','')
    publicKey = publicKey.replace('\r','')
    //println publicKey;
    String encrptPasswd = new String(EncryptUtil.encryptRSA('crystal08', publicKey));
    println 'encrptPasswd--------='+encrptPasswd;
    //println '---------------\n';
    postdata = postdata +'&username=mamingfeng007@outlook.com&password='+encrptPasswd;
    //println postdata;
    getHttpPost(postdata);

}

def getHttpPost(postData){
    def http = new HTTPBuilder('https://passport.bittest123.com/login')
    http.request(POST) {
        //uri.path = '/update'
        def body=postData
        def requestContentType= ContentType.URLENC
        headers.cookie = cookies;
        println '-----------'
        http.post( path: '/', body: body,
                requestContentType: requestContentType  ) { resp ->
            println resp.responseBase.statusLine
            resp.headers.each{language->
                //println language
            }
        }
    }
}
