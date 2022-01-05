import request from '@/utils/request'

export function getListFunc(data) {
  return request({
    url: '/system/func/list',
    method: 'post',
    data
  })
}