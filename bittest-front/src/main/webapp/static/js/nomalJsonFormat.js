/* 格式化JSON源码(对象转换为JSON文本) */
function formatJson(txt, compress/*是否为压缩模式*/) {
  if (/^\s*$/.test(txt)) {
    return 'formatError';
  }
  try {
    var data = JSON.parse(txt);
    var jsonStr = JSON.stringify(data, null, '~~!!~~!!');
    var reg = /~~!!~~!!/g;
    return jsonStr.replace(reg,'          ');
  }
  catch (e) {
    return 'formatError';
  }
}