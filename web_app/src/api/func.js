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

export function updateFunc(data) {
  return request({
    url: '/system/func',
    method: 'put',
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

export function getFunc(funcPk) {
  return request({
    url: '/system/func/' + funcPk,
    method: 'get'
  })
}

export function delFunc(funcPk) {
  return request({
    url: '/system/func/' + funcPk,
    method: 'delete'
  })
}
