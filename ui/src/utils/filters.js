import * as timeUtils from '@/utils/time';

/**
 * 时间格式化
 * @param date 时间戳
 * @returns {*}
 */
export function dateStr(date) {
  return timeUtils.dateDiff(date);
}

/**
 * 字符串截取
 * @param str 字符串
 * @param n 截至字符的下标
 * @returns {*}
 */
export function subString(str, n) {
  if (!n) return str;
  let sign = str.length <= n ? '' : '...';
  return str.substring(0, n) + sign;
}

