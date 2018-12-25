package groovy

import com.bittest.platform.bg.common.utils.EncryptUtil
import groovyx.net.http.HTTPBuilder
import org.cyberneko.html.parsers.SAXParser

import static groovyx.net.http.Method.GET
import static groovyx.net.http.ContentType.TEXT

def getHttpCompplex() {
    def http = new HTTPBuilder('https://passport.bittest123.com/login?locale=zh&service=https%3A%2F%2Fshop-repair-beta.bittest123.com.cn%2Fuser%2FafterSale%2FrepairHeaderList')


    http.request(GET, TEXT) { req ->
        //uri.path = '/mail/help/tasks/'
        headers.'User-Agent' = 'Mozilla/5.0'

        //请求成功
        response.success = { resp, reader ->
            assert resp.statusLine.statusCode == 200
            println "My response handler got response: ${resp.statusLine}"
            println "Response length: ${resp.headers.'Content-Length'}"
            //System.out << reader // print response stream

            def slurper = new XmlSlurper(new SAXParser())
            def htmlParser = slurper.parse(reader)

            htmlParser.'**'.findAll { it.@name == '_xsrf'  }.each {
                println '--------text:'+it.@value
            }

        }

        //404
        response.'404' = { resp ->
            println 'Not found'
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

def getHttp() {

    def slurper = new XmlSlurper(new SAXParser())
    def htmlParser = slurper.parse('https://passport.bittest123.com/login?locale=zh&service=https%3A%2F%2Fshop-repair-beta.bittest123.com.cn%2Fuser%2FafterSale%2FrepairHeaderList')
    def postdata = ''
    htmlParser.'**'.findAll { it.@name == '_xsrf'||it.@name == 'lt'||it.@name == 'public'||it.@name == 'service'  }.each {
        postdata = postdata + '&'+it.@name+'='+it.@value
    }
    println postdata;

    String publicKey = ''
    htmlParser.'**'.findAll { it.@name == 'public'}.each {
        // println  it+'--------'+it.@value
        publicKey =  it.@value
    }
    publicKey = publicKey.replace('-----BEGIN PUBLIC KEY-----' ,'')
    publicKey = publicKey.replace('-----END PUBLIC KEY-----','')
    publicKey = publicKey.replace('\n','')
    publicKey = publicKey.replace('\r','')
    println publicKey;
    String encrptPasswd = new String(EncryptUtil.encryptRSA('crystal08', publicKey));
    println 'encrptPasswd--------='+encrptPasswd;
    println '---------------\n';
    postdata = postdata +'&username=mamingfeng007@outlook.com&password='+encrptPasswd;
    println postdata;

}

static main(def args) {
    getHttp()
    //getHttpCompplex()
}