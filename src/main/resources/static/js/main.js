var API = Vue.resource('/valutes{/id}')
Vue.component('valutes-list', {
    props: ['valutes','input2'],
    data: function () {
        return {
            text: ''
        }
    },
    template: '<div>' +
        '<select v-model="text">' +
        '<option disabled value="">Выберите один из вариантов</option>-->' +
        '<option v-for="valute in valutes" :value="valute.id">{{valute.charCode}} ({{valute.name}})</option>' +
        '</select>' +
        '<span>Выбранная валюта: {{text}}</span>' +
        // '<button @click="save">SAVE</button>' +
        '</div>',
    created: function () {
        API.get().then(result =>
            result.json().then(data =>
                data.forEach(valute => this.valutes.push(valute))))
    },
    // methods: {
    //     save:function () {
    //         this.input2(this.text);
    //     }
    // },
    watch:{
        text:function () {
            this.input2(this.text);
        }
    }
});
Vue.component('calc',{
    props:['valute1','valute2'],
    data: function(){
        return{
            test:'',
            valute3:'',
            valute1FromServer:'',
            valute2FromServer:''
        }
    },
    template:'<div>' +
        '<div><button @click="calc()">KNOPKA</button></div>' +
        '<span>Result = {{test}}</span>' +
        '<span>valute1FromServer = {{valute1FromServer}}</span>' +
        '<span>valute2FromServer = {{valute2FromServer}}</span>' +
        '<div>RESULT = {{valute3}}</div>' +
        '</div>',
    methods: {
        calc: function () {
            this.test = this.valute1+this.valute2;
            this.getValute();
            var curs1 = this.valute1FromServer.value;
            var curs2 = this.valute2FromServer.value;
            var nom1 = this.valute1FromServer.nominal;
            var nom2 = this.valute2FromServer.nominal;
            this.valute3=(curs1*nom2)/(nom1*curs2);
        },
        getValute: function () {
            API.get({id:this.valute1}).then(resoult=>resoult.json().then(res=> this.valute1FromServer=res))
            API.get({id:this.valute2}).then(resoult=>resoult.json().then(res=> this.valute2FromServer=res))
        }
    }
});

var app = new Vue({
    el: '#app',
    data: {
        valutes: [],
        selected1: 'selected1',
        selected2: 'selected2',
        resoult: 'sss'
    },
    methods: {
        input: function () {
            API.get().then(resoult => {
                    this.curss = resoult.body;
                    console.log(resoult)
                }
            )
        },
        input1: function (resoult) {
            this.selected1 = resoult
        },
        input2: function (resoult) {
            this.selected2 = resoult
        }
    }
});