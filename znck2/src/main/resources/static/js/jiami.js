//word为需要加密的String字符
    function encrypt(word){
        //密钥--应和后台java解密或是前台js解密的密钥保持一致（16进制）
        var key = CryptoJS.enc.Utf8.parse("1234");
        //偏移量
        var srcs = CryptoJS.enc.Utf8.parse(word);
        //算法
        var encrypted = CryptoJS.AES.encrypt(srcs, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
        //替换--防止值为“1”的情况
        var reg = new RegExp('/',"g");
        return encrypted.toString().replace(reg,"#");
    }

    function decrypt(word){
        var key = CryptoJS.enc.Utf8.parse("1234");
        var decrypt = CryptoJS.AES.decrypt(word, key, {mode:CryptoJS.mode.ECB,padding: CryptoJS.pad.Pkcs7});
        return CryptoJS.enc.Utf8.stringify(decrypt).toString();
    }