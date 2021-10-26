package com.github.hmld.common.utils.ip;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hmld.common.constant.Constants;
import com.github.hmld.common.utils.StringUtils;

/**
 * 地址工具类
 * @author hmld
 *
 */
public class AddressUtils {

  // IP地址查询
  public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

  // 未知地址
  public static final String UNKNOWN = "XX XX";
  

  public static String getRealAddressByIP(String ip)
  {
      String address = UNKNOWN;
      // 内网不查询
      if (IpUtils.internalIp(ip))
      {
          return "内网IP";
      }else {
				return "外部IP";
			}
//      if (RuoYiConfig.isAddressEnabled())
//      {
//          try
//          {
//              String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
//              if (StringUtils.isEmpty(rspStr))
//              {
//                  return UNKNOWN;
//              }
//              ObjectMapper mapper = new ObjectMapper();
//              JsonNode obj = mapper.readTree(rspStr);
//              String region = obj.get("pro").asText();
//              String city = obj.get("city").asText();
//              return String.format("%s %s", region, city);
//          }
//          catch (Exception e)
//          {
//              log.error("获取地理位置异常 {}", ip);
//          }
//      }
//      return address;
  }
}
