!function(t){function e(i){if(n[i])return n[i].exports;var o=n[i]={i:i,l:!1,exports:{}};return t[i].call(o.exports,o,o.exports,e),o.l=!0,o.exports}var n={};e.m=t,e.c=n,e.i=function(t){return t},e.d=function(t,n,i){e.o(t,n)||Object.defineProperty(t,n,{configurable:!1,enumerable:!0,get:i})},e.n=function(t){var n=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(n,"a",n),n},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=9)}([function(t,e){var n={};t.exports=n,n.state={currentTargetComponent:"",deviceName:""},n.command={REQUEST_STORAGE_PERMISSION:"app_command/request_storege_permission",FINISH_APP_COMMAND:"app_command/finish_app",EXECUTE_HDD_FORMAT_COMMAND:"app_command/exec_hdd_format",COMPLETE_HDD_FORMAT_COMMAND:"app_command/hdd_format_complete"},n.commitCurrentTargetComponent=function(t){n.state.currentTargetComponent=t},n.commitDeviceName=function(t){n.state.deviceName=t}},function(t,e,n){function i(){m.register({keytype:m.SHORTCUT_KEYTYPE.UP,keydown:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"up",pressed:!0})},keyup:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"up",pressed:!1})}})}function o(){m.register({keytype:m.SHORTCUT_KEYTYPE.DOWN,keydown:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"down",pressed:!0})},keyup:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"down",pressed:!1})}})}function s(){m.register({keytype:m.SHORTCUT_KEYTYPE.RIGHT,keydown:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"right",pressed:!0})},keyup:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"right",pressed:!1})}})}function a(){m.register({keytype:m.SHORTCUT_KEYTYPE.LEFT,keydown:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"left",pressed:!0})},keyup:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"left",pressed:!1})}})}function r(){m.register({keytype:m.SHORTCUT_KEYTYPE.SUBMIT,keydown:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"submit",pressed:!0})},keyup:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"submit",pressed:!1})}})}function c(){m.register({keytype:m.SHORTCUT_KEYTYPE.RETURN,keydown:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"return",pressed:!0})},keyup:function(){l.eventBus.$emit(u.state.currentTargetComponent,{keytype:"return",pressed:!1})}})}const f=n(8),d=new f(f.Level.ERROR),m=n(7),u=n(0),l={};t.exports=l,l.initialize=function(){!0!==T&&(m.initialize(),i(),o(),s(),a(),r(),c(),m.start(),T=!0,d.info("RemoconEventHandler.initialize() end"))},l.eventBus=new Vue;var T=!1},function(t,e,n){const i=n(0),o={};t.exports=o,o.template=n(12),o.route={path:"/hdd-format-complete",components:{"index-content":o}},o.data=function(){return{sharedState:i.state}},o.methods={showCompleteMessage:function(t,e){var n="none",i="none",o="none";0!==t?i="block":0===t&&1===e?o="block":n="block";var s=document.getElementById("success_message1");s&&(s.style.display=n);var a=document.getElementById("failure_message1");a&&(a.style.display=i);var r=document.getElementById("success_message2");r&&(r.style.display=o)}},o.created=function(){i.commitCurrentTargetComponent("hddFormatComplete")},o.mounted=function(){var t=0,e=0,n=window.location.hash;if(null!==n){var i=n.split("?")[1];if(void 0!==i){var o=i.split("&"),s=o.reduce(function(t,e){var n=e.split("=");return t[n[0]]=n[1],t},{});void 0!==s.result&&(t=parseInt(s.result,10)),void 0!==s.messageId&&(e=parseInt(s.messageId,10))}}this.showCompleteMessage(t,e)},o.activated=function(){i.commitCurrentTargetComponent("hddFormatComplete")}},function(t,e,n){const i=n(5),o=n(6),s=n(0),a=n(1),r={};t.exports=r;r.template=n(13),r.route={path:"/hdd-format-start",components:{"index-content":r}},r.components={"confirm-dialog":i,"message-dialog":o},r.data=function(){return{sharedState:s.state,focus:"cancelFormat",deviceName:s.state.deviceName,showFormatComfirmDialog:!1,confirmMessageList:[],warningMessageList:[],messageTextList:[{text:"このハードディスクはフォーマット済みです。",isArib:!1},{text:"フォーマットは必要ありませんので、キャンセルします。",isArib:!1}],confirmDialogFocusButton:"cancel",messageDialogFocusbutton:"submit",showMode:0}},r.methods={clickCancelFormat:function(){this.finishApp()},clickExecFormat:function(){var t=this.getDeviceName();this.confirmDialogFocusButton="cancel",this.confirmMessageList=[{text:"["+t+"] のフォーマットを実行します。",isArib:!1},{text:"本当によろしいですか?",isArib:!1}],this.warningMessageList=[{text:"　　　　　"},{text:"フォーマットすると、現在このドライブに保管されている"},{text:"すべてのデータが消去されます。"}],this.showFormatComfirmDialog=!0},startFormat:function(){window.location.href=s.command.EXECUTE_HDD_FORMAT_COMMAND},getDeviceName:function(){var t=document.getElementById("device_name");return null!==t?t.innerText:""},finishApp:function(){window.location.href=s.command.FINISH_APP_COMMAND},confirmDialogSubmit:function(t){const e=this;switch(t){case"submit":e.showFormatComfirmDialog=!1,setTimeout(function(){e.showFormatComfirmDialog=!1},100),e.startFormat();break;case"cancel":e.showFormatComfirmDialog=!1,setTimeout(function(){e.showFormatComfirmDialog=!1},100)}},clickMessageDialogItem:function(){this.hideMessageDialog(),this.finishApp()},isVisibleMessageDialog:function(){var t=document.getElementById("message_dialog");return null!==t&&"none"!==t.style.display},hideMessageDialog:function(){var t=document.getElementById("message_dialog");null!==t&&(t.style.display="none")},dispatchKeyEvent:function(t){const e=this;if(!0===t.pressed)switch(t.keytype){case"up":case"down":if(!0===e.showFormatComfirmDialog)switch(e.confirmDialogFocusButton){case"submit":e.confirmDialogFocusButton="cancel";break;case"cancel":e.confirmDialogFocusButton="submit"}else if(!0===e.isVisibleMessageDialog())e.messageDialogFocusbutton="submit";else switch(e.focus){case"cancelFormat":e.focus="execFormat";break;case"execFormat":e.focus="cancelFormat"}break;case"submit":if(!0===e.showFormatComfirmDialog)e.confirmDialogSubmit(e.confirmDialogFocusButton);else if(!0===e.isVisibleMessageDialog())e.clickMessageDialogItem();else switch(e.focus){case"cancelFormat":e.clickCancelFormat();break;case"execFormat":e.clickExecFormat()}break;case"return":!0===e.showFormatComfirmDialog?e.confirmDialogSubmit("cancel"):!0===e.isVisibleMessageDialog()?e.clickMessageDialogItem():e.clickCancelFormat()}}},r.computed={cencelFormatObject:function(){return{focus:"cancelFormat"===this.focus}},execFormatObject:function(){return{focus:"execFormat"===this.focus}}},r.created=function(){const t=this;s.commitCurrentTargetComponent("hddFormatStart"),a.eventBus.$on("hddFormatStart",function(e){t.dispatchKeyEvent(e)}),t.deviceName=t.getDeviceName();var e=window.location.hash;if(null!==e){var n=e.split("?")[1];if(void 0!==n){var i=n.split("&"),o=i.reduce(function(t,e){var n=e.split("=");return t[n[0]]=n[1],t},{});void 0!==o.showMode&&(t.showMode=parseInt(o.showMode,10))}}},r.activated=function(){s.commitCurrentTargetComponent("hddFormatStart")},r.destroyed=function(){this.showFormatComfirmDialog=!1,a.eventBus.$off("hddFormatStart")}},function(t,e,n){const i=n(0),o={};t.exports=o;o.template=n(14),o.route={path:"/hdd-format-formatting",components:{"index-content":o}},o.data=function(){return{sharedState:i.state,progress:0}},o.methods={nextPage:function(){i.router.push("/hdd-format-complete")}},o.created=function(){this.progress=0,i.commitCurrentTargetComponent("hddFormatting")},o.activated=function(){i.commitCurrentTargetComponent("hddFormatting")}},function(t,e,n){var i={};t.exports=i,i.template=n(10),i.props={dialogWidth:{type:String,required:!0},dialogHeight:{type:String,required:!0},dialogMinHeight:{type:String,default:"0"},dialogMaxHeight:{type:String,default:"none"},dialogPadding:{type:String,required:!0},messageFontSize:{type:String,required:!0},messageLineHeight:{type:String,required:!0},messageTextAlign:{type:String,default:"start"},messageInfoList:{type:Array,required:!0},warningFontSize:{type:String},warningLineHeight:{type:String},warningInfoList:{type:Array,default:function(){return[]}},buttonWidth:{type:String,default:"100%"},buttonHeight:{type:String,required:!0},buttonMarginTop:{type:String,default:"0"},buttonFontSize:{type:String,required:!0},buttonFocus:{type:String,required:!0},buttonTextSubmit:{type:String,default:"はい"},buttonTextCancel:{type:String,default:"いいえ"},dialogBorder:{type:String,default:"none"},dialogBackgroundColor:{type:String,default:"#000000"}},i.data=function(){var t=this;return{dialogStyleObject:{width:t.dialogWidth,height:t.dialogHeight,minHeight:t.dialogMinHeight,maxHeight:t.dialogMaxHeight,padding:t.dialogPadding,border:t.dialogBorder,backgroundColor:t.dialogBackgroundColor},messageStyleObject:{fontSize:t.messageFontSize,lineHeight:t.messageLineHeight,textAlign:t.messageTextAlign},warningStyleObject:{fontSize:t.warningFontSize,lineHeight:t.warningLineHeight},buttonStyleObject:{width:t.buttonWidth,height:t.buttonHeight,fontSize:t.buttonFontSize},buttonMarginTopStyleObject:{height:t.buttonMarginTop}}},i.methods={click:function(t){this.$emit("click",t)},mouseEnter:function(t){this.$emit("mouseenter",t)},mouseLeave:function(t){this.$emit("mouseleavae",t)}}},function(t,e,n){var i={};t.exports=i,i.template=n(11),i.props={dialogWidth:{type:String,required:!0},dialogHeight:{type:String,required:!0},dialogPadding:{type:String,required:!0},messageFontSize:{type:String,required:!0},messageLineHeight:{type:String,required:!0},messageInfoList:{type:Array,required:!0},buttonText:{type:String,default:"閉じる"},buttonWidth:{type:String,default:"100%"},buttonHeight:{type:String,required:!0},buttonMarginLeft:{type:String,default:"0%"},buttonFontSize:{type:String,required:!0},buttonFocus:{type:String,required:!0},dialogBorder:{type:String,default:"none"},dialogBackgroundColor:{type:String,default:"#000000"},messageMarginBottom:{type:Array,default:null}},i.data=function(){var t=this;return{dialogStyleObject:{width:t.dialogWidth,height:t.dialogHeight,padding:t.dialogPadding,border:t.dialogBorder,backgroundColor:t.dialogBackgroundColor},buttonStyleObject:{width:t.buttonWidth,height:t.buttonHeight,fontSize:t.buttonFontSize,marginLeft:t.buttonMarginLeft}}},i.computed={messageStyleObject:function(){const t=this;return function(e){var n={};if(n.fontSize=t.messageFontSize,n.lineHeight=t.messageLineHeight,null!==t.messageMarginBottom&&t.messageMarginBottom.length>0)for(var i=0;i<t.messageMarginBottom.length;i++)if(e===t.messageMarginBottom[i].line)return n.marginBottom=t.messageMarginBottom[i].marginBottom,n;return n}}},i.methods={click:function(t){this.$emit("click",t)},mouseEnter:function(t){this.$emit("mouseenter",t)}}},function(t,e){function n(t,e){if(t.keyCode in s){var n=!0===t.shiftKey?"shifton":"shiftoff";if(n in s[t.keyCode]){var i=s[t.keyCode][n],o=c[i][e];null!==o&&o(t)}}}var i={};t.exports=i,i.SHORTCUT_KEYTYPE={CH1:1,CH2:2,CH3:3,CH4:4,CH5:5,CH6:6,CH7:7,CH8:8,CH9:9,CH10:10,CH11:11,CH12:12,CH0:13,MUTE:14,VOLUME_UP:15,VOLUME_DOWN:16,CHANGE_BR:17,CHANNEL_UP:18,CHANNEL_DOWN:19,ASTERISK:20,HASH:21,CLEAR:22,DIGIT_INPUT:23,SHOW:24,RETURN:25,END:26,UP:27,DOWN:28,RIGHT:29,LEFT:30,SUBMIT:31,MENU:32,DATA:33,CHANNEL_LIST:34,BLUE:35,RED:36,GREEN:37,YELLOW:38,SKIP_REVERSE:39,PAUSE:40,SKIP_FORWARD:41,FAST_REVERSE:42,PLAY:43,FAST_FORWARD:44,RECORD:45,STOP:46,PREV_CHAPTER:47,NEXT_CHAPTER:48,CHANGE_VOICE:49,CHANGE_IMAGE:50,CHANGE_SUBTITLE:51,TV_APP:52,RESERVE_LIST:53,RECORD_LIST:54,PROGRAM_LIST:55,TR:56,BS:57,CS:58,SCREEN_DISPLAY:59,SYSTEM_INFO:60,CLOSE_MENU:61,BSCS_4K:62,CHANGE_BSCS:63,DOUBLE_RIGHT:64,DOUBLE_LEFT:65,CHANGE_BS2K4K:66,PREVIEW_SETTING:67},i.initialize=function(){if(!0!==o){for(var t in this.SHORTCUT_KEYTYPE)c[this.SHORTCUT_KEYTYPE[t]]={keydown:null,keyup:null};o=!0}},i.start=function(){this.stop(),a=function(t){n(t,"keydown")},r=function(t){n(t,"keyup")},window.addEventListener("keydown",a),window.addEventListener("keyup",r)},i.stop=function(){null!==a&&(window.removeEventListener("keydown",a),a=null),null!==r&&(window.removeEventListener("keyup",r),r=null)},i.register=function(t){return"keytype"in t&&"keydown"in t&&"keyup"in t&&(t.keytype in c&&(c[t.keytype].keydown=t.keydown,c[t.keytype].keyup=t.keyup,!0))};var o=!1,s={8:{shiftoff:i.SHORTCUT_KEYTYPE.RETURN},13:{shiftoff:i.SHORTCUT_KEYTYPE.SUBMIT},27:{shiftoff:i.SHORTCUT_KEYTYPE.CLEAR},35:{shiftoff:i.SHORTCUT_KEYTYPE.DOUBLE_RIGHT},36:{shiftoff:i.SHORTCUT_KEYTYPE.DOUBLE_LEFT},37:{shiftoff:i.SHORTCUT_KEYTYPE.LEFT},38:{shiftoff:i.SHORTCUT_KEYTYPE.UP},39:{shiftoff:i.SHORTCUT_KEYTYPE.RIGHT},40:{shiftoff:i.SHORTCUT_KEYTYPE.DOWN},48:{shiftoff:i.SHORTCUT_KEYTYPE.CH0,shifton:i.SHORTCUT_KEYTYPE.CH10},49:{shiftoff:i.SHORTCUT_KEYTYPE.CH1,shifton:i.SHORTCUT_KEYTYPE.CH11},50:{shiftoff:i.SHORTCUT_KEYTYPE.CH2,shifton:i.SHORTCUT_KEYTYPE.CH12},51:{shiftoff:i.SHORTCUT_KEYTYPE.CH3,shifton:i.SHORTCUT_KEYTYPE.HASH},52:{shiftoff:i.SHORTCUT_KEYTYPE.CH4},53:{shiftoff:i.SHORTCUT_KEYTYPE.CH5},54:{shiftoff:i.SHORTCUT_KEYTYPE.CH6},55:{shiftoff:i.SHORTCUT_KEYTYPE.CH7},56:{shiftoff:i.SHORTCUT_KEYTYPE.CH8,shifton:i.SHORTCUT_KEYTYPE.ASTERISK},57:{shiftoff:i.SHORTCUT_KEYTYPE.CH9},65:{shiftoff:i.SHORTCUT_KEYTYPE.CHANGE_VOICE,shifton:i.SHORTCUT_KEYTYPE.BSCS_4K},66:{shiftoff:i.SHORTCUT_KEYTYPE.SHOW,shifton:i.SHORTCUT_KEYTYPE.BS},67:{shifton:i.SHORTCUT_KEYTYPE.CS},68:{shiftoff:i.SHORTCUT_KEYTYPE.DATA,shifton:i.SHORTCUT_KEYTYPE.TR},69:{shiftoff:i.SHORTCUT_KEYTYPE.CH10,shifton:i.SHORTCUT_KEYTYPE.CHANGE_BS2K4K},70:{shiftoff:i.SHORTCUT_KEYTYPE.CHANGE_BR,shifton:i.SHORTCUT_KEYTYPE.CHANGE_BSCS},73:{shiftoff:i.SHORTCUT_KEYTYPE.CH11},74:{shiftoff:i.SHORTCUT_KEYTYPE.PAUSE},75:{shiftoff:i.SHORTCUT_KEYTYPE.CHANGE_SUBTITLE},76:{shiftoff:i.SHORTCUT_KEYTYPE.SCREEN_DISPLAY},77:{shiftoff:i.SHORTCUT_KEYTYPE.MUTE},78:{shiftoff:i.SHORTCUT_KEYTYPE.CH12},80:{shiftoff:i.SHORTCUT_KEYTYPE.PLAY},81:{shiftoff:i.SHORTCUT_KEYTYPE.END},82:{shiftoff:i.SHORTCUT_KEYTYPE.RECORD},83:{shiftoff:i.SHORTCUT_KEYTYPE.STOP},86:{shiftoff:i.SHORTCUT_KEYTYPE.CHANGE_IMAGE},87:{shiftoff:i.SHORTCUT_KEYTYPE.SYSTEM_INFO},88:{shiftoff:i.SHORTCUT_KEYTYPE.FAST_FORWARD},90:{shiftoff:i.SHORTCUT_KEYTYPE.FAST_REVERSE},93:{shiftoff:i.SHORTCUT_KEYTYPE.MENU,shifton:i.SHORTCUT_KEYTYPE.CLOSE_MENU},107:{shiftoff:i.SHORTCUT_KEYTYPE.CHANNEL_UP},109:{shiftoff:i.SHORTCUT_KEYTYPE.CHANNEL_DOWN},112:{shiftoff:i.SHORTCUT_KEYTYPE.SHOW},113:{shiftoff:i.SHORTCUT_KEYTYPE.PROGRAM_LIST},114:{shiftoff:i.SHORTCUT_KEYTYPE.RESERVE_LIST},115:{shiftoff:i.SHORTCUT_KEYTYPE.RECORD_LIST},116:{shiftoff:i.SHORTCUT_KEYTYPE.BLUE},117:{shiftoff:i.SHORTCUT_KEYTYPE.RED},118:{shiftoff:i.SHORTCUT_KEYTYPE.GREEN},119:{shiftoff:i.SHORTCUT_KEYTYPE.YELLOW},120:{shiftoff:i.SHORTCUT_KEYTYPE.TV_APP},122:{shiftoff:i.SHORTCUT_KEYTYPE.CHANNEL_LIST},123:{shiftoff:i.SHORTCUT_KEYTYPE.MENU,shifton:i.SHORTCUT_KEYTYPE.CLOSE_MENU},124:{shiftoff:i.SHORTCUT_KEYTYPE.SCREEN_DISPLAY},166:{shiftoff:i.SHORTCUT_KEYTYPE.RETURN},173:{shiftoff:i.SHORTCUT_KEYTYPE.MUTE},174:{shiftoff:i.SHORTCUT_KEYTYPE.VOLUME_DOWN},175:{shiftoff:i.SHORTCUT_KEYTYPE.VOLUME_UP},176:{shiftoff:i.SHORTCUT_KEYTYPE.SKIP_FORWARD},177:{shiftoff:i.SHORTCUT_KEYTYPE.SKIP_REVERSE},178:{shiftoff:i.SHORTCUT_KEYTYPE.STOP},186:{shifton:i.SHORTCUT_KEYTYPE.ASTERISK},187:{shiftoff:i.SHORTCUT_KEYTYPE.CHANNEL_UP},188:{shiftoff:i.SHORTCUT_KEYTYPE.VOLUME_DOWN},189:{shiftoff:i.SHORTCUT_KEYTYPE.CHANNEL_DOWN},190:{shiftoff:i.SHORTCUT_KEYTYPE.VOLUME_UP},219:{shiftoff:i.SHORTCUT_KEYTYPE.PREVIEW_SETTING},220:{shiftoff:i.SHORTCUT_KEYTYPE.DIGIT_INPUT}},a=null,r=null,c={}},function(t,e,n){"use strict";function i(t){return t.split(/\/|\\/).pop()}function o(t,e){return JSON.stringify(t,e).replace(/\\n/g,"\n")}function s(t,e){return"password"===t?"xxxxxxx":e}function a(t,e){var n={level:e,message:t};console.info(o(n))}var r=function(t){t>=r.Level.FATAL&&t<=r.Level.DEBUG?this.l=t:this.l=r.Level.FATAL};r.Level={DEBUG:4,INFO:3,WARN:2,ERROR:1,FATAL:0},r.prototype.enter=function(t,e,n){var a={level:"[I]",function:"I:"+i(t),event:e,context:n};console.info(o(a,s))},r.prototype.leaveS=function(t,e){var n={level:"[I]",function:"O:"+i(t),message:e};console.info(o(n))},r.prototype.leaveE=function(t,e){var n={level:"[E]",function:"O:"+i(t),message:e};console.info(o(n))},r.prototype.debug=function(t){this.l>=r.Level.DEBUG&&a(t,"[D]")},r.prototype.info=function(t){this.l>=r.Level.INFO&&a(t,"[I]")},r.prototype.warn=function(t){this.l>=r.Level.WARN&&a(t,"[W]")},r.prototype.error=function(t){this.l>=r.Level.ERROR&&a(t,"[E]")},r.prototype.fatal=function(t){this.l>=r.Level.FATAL&&a(t,"[F]")},t.exports=r},function(t,e,n){var i=n(0),o=n(1);const s=n(3),a=n(4),r=n(2);o.initialize();const c=new VueRouter({mode:"hash",routes:[s.route,a.route,r.route]});i.router=c;new Vue({el:"#app",router:c,data:{sharedState:i.state},components:{"hdd-format-start":s,"hdd-formatting":a,"hdd-format-complete":r},methods:{}})},function(t,e){t.exports='<div>\n\t<div class="comp-confirm-dialog" v-bind:style="dialogStyleObject">\n\n\t\t\x3c!-- 表示メッセージ --\x3e\n\t\t<div>\n\t\t\t<template v-for="msgInfo in messageInfoList">\n\t\t\t\t<p class="comp-confirm-dialog_message"\n\t\t\t\t   v-bind:class="{ arib: msgInfo.isArib === true, ellipsis: msgInfo.isEllipsis === true }"\n\t\t\t\t   v-bind:style="messageStyleObject">{{ msgInfo.text }}</p>\n\t\t\t</template>\n\t\t</div>\n\n\t\t\x3c!-- 警告メッセージ --\x3e\n\t\t<div>\n\t\t\t<template v-for="warnInfo in warningInfoList">\n\t\t\t\t<p class="comp-confirm-dialog_warning"\n\t\t\t\t   v-bind:style="warningStyleObject">{{ warnInfo.text }}</p>\n\t\t\t</template>\n\t\t</div>\n\t\t\n\t\t<div class="comp-confirm-dialog_button-margin-top"\n\t\t\t v-bind:style="buttonMarginTopStyleObject">\n\t\t</div>\n\n\t\t\x3c!-- ボタン --\x3e\n\t\t<div>\n\t\t\t<div class="comp-confirm-dialog_button"\n\t\t\t\t v-bind:class="{ focus: buttonFocus === \'submit\' }"\n\t\t\t\t v-bind:style="buttonStyleObject"\n\t\t\t\t v-on:click="click(\'submit\')"\n\t\t\t\t v-on:mouseenter="mouseEnter(\'submit\')"\n\t\t\t\t v-on:mouseleave="mouseLeave(\'submit\')">{{ buttonTextSubmit }}</div>\n\t\t\t<div class="comp-confirm-dialog_button"\n\t\t\t\t v-bind:class="{ focus: buttonFocus === \'cancel\' }"\n\t\t\t\t v-bind:style="buttonStyleObject"\n\t\t\t\t v-on:click="click(\'cancel\')"\n\t\t\t\t v-on:mouseenter="mouseEnter(\'cancel\')"\n\t\t\t\t v-on:mouseleave="mouseLeave(\'cancel\')">{{ buttonTextCancel }}</div>\n\t\t</div>\n\n\t</div>\n</div>'},function(t,e){t.exports='<div>\n\t<div class="comp-message-dialog" v-bind:style="dialogStyleObject">\n\n\t\t\x3c!-- 表示メッセージ --\x3e\n\t\t<div>\n\t\t\t<template v-for="(msgInfo, index) in messageInfoList">\n\t\t\t\t<p class="comp-message-dialog_message"\n\t\t\t\t   v-bind:class="{ arib: msgInfo.isArib === true }"\n\t\t\t\t   v-bind:style="messageStyleObject(index)">{{ msgInfo.text }}</p>\n\t\t\t</template>\n\t\t</div>\n\n\t\t\x3c!-- ボタン --\x3e\n\t\t<div>\n\t\t\t<div class="comp-message-dialog_button"\n\t\t\t\t v-bind:class="{ focus: buttonFocus === \'submit\' }"\n\t\t\t\t v-bind:style="buttonStyleObject"\n\t\t\t\t v-on:click="click(\'submit\')"\n\t\t\t\t v-on:mouseenter="mouseEnter(\'submit\')">{{ buttonText }}</div>\n\t\t</div>\n\n\t</div>\n</div>'},function(t,e){t.exports='<div>\n\t<div class="comp-hdd-format-complete">\n\t\t<div class="comp-hdd-format-complete_main">\n\t\t\t<div class="title" id="success_message1">\n\t\t\t\t<p>フォーマットが完了しました。</p>\n\t\t\t\t<p>ハードディスクの接続が確認できるまでお待ちください。</p>\n\t\t\t</div>\n\t\t\t<div class="title" id="failure_message1">\n\t\t\t\t<p>フォーマットに失敗しました。</p>\n\t\t\t</div>\n\t\t\t<div class="title" id="success_message2">\n\t\t\t\t<p>フォーマットが完了しました。</p>\n\t\t\t\t<p>本体を再起動します。</p>\n\t\t\t\t<p>次の画面で restart を選択してください。</p>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n</div>'},function(t,e){t.exports='<div>\n\t<div class="comp-hdd-format-start">\n\t\t<div class="comp-hdd-format-start_main">\n\t\t\t<div class="comp-hdd-format-start_main_message">\n\t\t\t\t<p class="title" v-if="showMode === 0">対応しない形式のドライブが検出されました。</p>\n\t\t\t\t<p class="subtitle" >[<span id="device_name">{{ deviceName }}</span>]&nbsp;を、Xit用ストレージとしてフォーマットしますか?</p><br>\n\t\t\t\t<p class="discription">このドライブはフォーマット後、Xitで使用できるようになります。<br>フォーマットすると、現在このドライブに保管されているすべてのデータが消去されます。</p>\n\t\t\t</div>\n\t\t\t<div class="comp-hdd-format-start_main_operation">\n\t\t\t\t<div class="comp-hdd-format-start_main_operation_cancel" v-bind:class="cencelFormatObject" \n\t\t\t\t\tv-on:mouseenter="focus = \'cancelFormat\'" v-on:mouseleave="focus = \'\'" \n\t\t\t\t\tv-on:click.stop="clickCancelFormat">\n\t\t\t\t\t<div class="text">キャンセル</div>\n\t\t\t\t</div>\n\t\t\t\t<div class="comp-hdd-format-start_main_operation_format" v-bind:class="execFormatObject" \n\t\t\t\t\tv-on:mouseenter="focus = \'execFormat\'" v-on:mouseleave="focus = \'\'" \n\t\t\t\t\tv-on:click.stop="clickExecFormat">\n\t\t\t\t\t<div class="text">フォーマットする</div>\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t</div>\n\t\t\x3c!-- 実行確認ダイアログ --\x3e\n\t\t<div class="comp-hdd-format-start_modal" v-if="showFormatComfirmDialog">\n\t\t\t\t<confirm-dialog class="comp-hdd-format-start_modal_confirm-dialog" \n\t\t\t\t\tdialog-width="100%" \n\t\t\t\t\tdialog-height="100%" \n\t\t\t\t\tdialog-padding="5% 12.5%"\n\t\t\t\t\tdialog-border="1px solid #666666"\n\t\t\t\t\tmessage-font-size="1.66vw"\n\t\t\t\t\tv-bind:message-info-list="confirmMessageList"\n\t\t\t\t\twarning-font-size="1.66vw"\n\t\t\t\t\tv-bind:warning-info-list="warningMessageList" \n\t\t\t\t\tbutton-height="10vh"\n\t\t\t\t\tbutton-font-size="1.25vw"\n\t\t\t\t\tv-bind:button-focus="confirmDialogFocusButton"\n\t\t\t\t\tv-on:click="confirmDialogSubmit">\n\t\t\t\t</confirm-dialog>\n\t\t</div>\n\t\t\x3c!-- メッセージダイアログ（外部から表示している） --\x3e\n\t\t<div class="comp-hdd-format-start_modal" id="message_dialog" style="display:none">\n\t\t\t<message-dialog class="comp-hdd-format-start_modal_message-dialog"\n\t\t\t\t\t\t\t\t\tdialog-width="fit-content"\n\t\t\t\t\t\t\t\t\tdialog-height="300px"\n\t\t\t\t\t\t\t\t\tdialog-padding="48px 48px 48px 48px"\n\t\t\t\t\t\t\t\t\tmessage-font-size="18px"\n\t\t\t\t\t\t\t\t\tmessage-line-height="34px"\n\t\t\t\t\t\t\t\t\tv-bind:message-info-list="messageTextList"\n\t\t\t\t\t\t\t\t\tbutton-width="442px"\n\t\t\t\t\t\t\t\t\tbutton-height="60px"\n\t\t\t\t\t\t\t\t\tbutton-font-size="18px"\n\t\t\t\t\t\t\t\t\tv-bind:button-focus="messageDialogFocusbutton"\n\t\t\t\t\t\t\t\t\tv-on:click="clickMessageDialogItem"></message-dialog>\n\t\t</div>\n\t</div>\n</div>'},function(t,e){t.exports='<div>\n\t<div class="comp-hdd-format-formatting">\n\t\t<p class="title">ドライブをフォーマットしています&hellip;</p>\n\t\t<div class="comp-hdd-format-formatting_main">\n\t\t\t<p class="comp-hdd-format-formatting_main_percentage" id="progress_text">{{ progress }}%</p>\n\t\t\t<div class="comp-hdd-format-formatting_main_progress">\n\t\t\t<div class="comp-hdd-format-formatting_main_progress_bar" v-bind:style="{ width: progress + \'%\' }" id="progress_bar"></div>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n</div>'}]);