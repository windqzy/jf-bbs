import * as timeUtils from '@/utils/time';

/**
 * 时间格式化
 * @param date 时间戳
 * @returns {*}
 */
export function dateStr(date) {
  return timeUtils.dateDiff(date);
}
