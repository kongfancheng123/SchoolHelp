<template>
  <div class="msg">
    <p v-for="(val,index) in txtArr"
       :key="index">
      {{val}}
    </p>
  </div>
</template>

<script>
import Stomp from 'stompjs'
import SockJS from 'sockjs-client'

export default {
  data() {
    return {
      stompClient: null,
      txtArr: []
    }
  },
  methods: {},
  created() {
    let vm = this
    if (vm.stompClient === null) {
      let socket = new SockJS('/webSocket')
      vm.stompClient = Stomp.over(socket)
      vm.stompClient.connect(
        {},
        function(frame) {
          vm.stompClient.subscribe('/topic/pullLogger', function(event) {
            let content = JSON.parse(event.body)
            let str =
              content.timestamp +
              ' ' +
              content.level +
              ' --- [' +
              content.threadName +
              '] ' +
              content.className +
              '   :' +
              content.body

            vm.txtArr.push(str)

            vm.$nextTick(() => {
              if (document.querySelector('.msg')) {
                let sh = document.querySelector('.msg').scrollHeight
                document.querySelector('.msg').scrollTop = sh
              }
            })
          })
        }
      )
    }
  },
  destroyed() {
    let vm = this
    if (vm.stompClient !== null) {
      vm.stompClient.disconnect()
      vm.stompClient = null
    }
  }
}
</script>

<style lang="scss" scoped>
.msg {
  height: 100%;
  background: #333;
  width: 100%;
  border: 30px solid #fff;
  box-sizing: border-box;
  color: #fff;
  overflow: auto;
  p {
    font-size: 14px;
    padding: 5px;
  }
}
</style>
