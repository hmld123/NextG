import request from '@/utils/request'

export function getListFunc(data) {
  return request({
    url: '/system/func/list',
    method: 'post',
    data
  })
}

export function saveFunc(data) {
  return request({
    url: '/system/func',
    method: 'post',
    data
  })
}

export function listMenu(data) {
  return request({
    url: '/system/func/menu',
    method: 'post',
    data
  })
}
