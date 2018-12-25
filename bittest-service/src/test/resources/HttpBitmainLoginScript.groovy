import com.bittest.platform.bg.common.utils.EncryptUtil
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.cyberneko.html.parsers.SAXParser

import static groovyx.net.http.Method.GET
import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.TEXT
def exe() {
    def http = new HTTPBuilder('https://passport.bittest123.com/login?locale=zh&service=test')
    http.request(GET, TEXT) { req ->

        def headerMap = [:]
        //请求成功
        response.success = { resp, reader ->
            List<String> cookies = []
            resp.getHeaders('Set-Cookie').each {
                String cookie = it.value.split(';')[0]
                cookies.add(cookie)
            }
            def slurper = new XmlSlurper(new SAXParser())
            def htmlParser = slurper.parse(reader)
            def postdata = ''
            htmlParser.'**'.findAll { it.@name == '_xsrf'||it.@name == 'lt'||it.@name == 'public'||it.@name == 'service'  }.each {
                postdata = postdata + '&'+it.@name+'='+it.@value
            }
            String publicKey = ''
            htmlParser.'**'.findAll { it.@name == 'public'}.each {
                publicKey =  it.@value
            }
            publicKey = publicKey.replace('-----BEGIN PUBLIC KEY-----' ,'')
            publicKey = publicKey.replace('-----END PUBLIC KEY-----','')
            publicKey = publicKey.replace('\n','')
            publicKey = publicKey.replace('\r','')
            String encrptPasswd = new String(EncryptUtil.encryptRSA('crystal08', publicKey))
            encrptPasswd = URLEncoder.encode(encrptPasswd)
            postdata = postdata +'&username=mamingfeng007@outlook.com&password='+encrptPasswd

            headerMap['cookie']= cookies.join(';')
            loginRequest(postdata,headerMap)
            return headerMap
        }
    }
}

def loginRequest(postData,headerMap){
    def http = new HTTPBuilder('https://passport.bittest123.com/login')
    http.request(POST, ContentType.URLENC) {
        headers.'cookie' = headerMap.cookie
        body = postData
        response.success = { resp, reader ->
            if(resp.responseBase.statusLine.statusCode==302){
                String location = resp.getHeaders('Location')[0].value
                 def locationValue = location.split('\\?')[1]
                def cookies = headerMap.cookie + ";"+locationValue
                headerMap['cookie']= cookies
                loginCAS(headerMap)
            }
        }
    }
}

def loginCAS(headerMap){
    def http = new HTTPBuilder('https://shop-base-beta.bittest123.com/api/casLogin')
    http.request(GET, ContentType.ANY) {
        headers.'cookie' = headerMap.cookie
        headers.'referer' = 'test'
        List<String> logincookies = [];
        response.success = { resp, reader ->
            println reader.text
            resp.getHeaders('Set-Cookie').each {
                String cookie = it.value.split(';')[0]
                logincookies.add(cookie)
            }
            def cookies = headerMap.cookie +";"+logincookies.join(';')
            headerMap['cookie']= cookies
        }
    }
}
static main(def args) {
  exe()
}

def getShopcartList(cookies){
    def http = new HTTPBuilder('https://shop-order-beta.bittest123.com/api/shopcart/list')
    http.request(GET, ContentType.JSON) {
        headers.'cookie' = cookies
        //请求成功
        response.success = { resp, reader ->
            println reader
        }
    }
}


