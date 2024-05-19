# SimpleHwid
一个简单的Hwid示例

## 如何使用
调用MathUtils里的`AES(string, keyword)`进行加密

调用MathUtils里的`AntiAES(string, keyword)`进行解密

注意: AES加密后的结果被Base64编码，若无次需要可以自行删除

## 加密原理
AES算法

## 关于改进
您可以自己添加代码进行改进

如: 在AES加密前将字符串先进行凯撒编码
