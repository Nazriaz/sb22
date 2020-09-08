var valuteAPI = Vue.resource('/valutes');
var cursesAPI = Vue.resource('/curses{/id}');
var APIhistory = Vue.resource('/history{/id}');
Vue.component('valutes-list', {
    props: ['valutes', 'input2'],
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
        '</div>',
    created: function () {
        valuteAPI.get().then(result =>
            result.json().then(data =>
                data.forEach(valute => this.valutes.push(valute))))
    },
    watch: {
        text: function () {
            this.input2(this.text);
        }
    }
});
Vue.component('calc', {
    props: ['valute1', 'valute2', 'history-arr'],
    data: function () {
        return {
            amount: 1,
            valute3: '',
            valute1FromServer: '',
            valute2FromServer: ''
        }
    },
    template: '<div>' +
        '<div><button @click="calc">CONVERT</button></div>' +
        '<div><input v-model="amount"></div>' +
        '<div>RESULT = {{valute3}}</div>' +
        '</div>',
    methods: {
        calc: function () {
            const promis1 = cursesAPI.get({id: this.valute1}).then(resoult => resoult.json().then(res => this.valute1FromServer = res));
            const promis2 = cursesAPI.get({id: this.valute2}).then(resoult => resoult.json().then(res => this.valute2FromServer = res));
            Promise.all([promis1, promis2]).then(values => {
                var curs1 = this.valute1FromServer.value;
                var curs2 = this.valute2FromServer.value;
                var nom1 = this.valute1FromServer.nominal;
                var nom2 = this.valute2FromServer.nominal;
                this.valute3 = ((curs1 * nom2) * this.amount) / (nom1 * curs2);
                this.valute3=Math.round(this.valute3 * 10000) / 10000;
                this.send(this.valute1FromServer.valuteName, this.valute2FromServer.valuteName, this.amount, this.valute3, this.valute1FromServer.date);
            })
        },
        send: function (val1, val2, am1, am2, dat) {
            var item = {valuteFrom: val1, valuteTo: val2, amountFrom: am1, amountTo: am2, date: dat};
            APIhistory.save(item).then(this.historyArr.push(item));
        },
    }
});
Vue.component('history', {
    props: ['history-arr'],
    template: '<div>' +
        '<table>' +
        '<tr >' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">valuteFrom</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">valuteTo</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">amountFrom</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">amountTo</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">date</th>' +
        '</tr v-bind:style="{ border: \'2px solid gray\'}">' +
        '<tr v-for="history in historyArr">' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">{{history.valuteFrom}}</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">{{history.valuteTo}}</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">{{history.amountFrom}}</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">{{history.amountTo}}</th>' +
        '<th v-bind:style="{ border: \'2px solid gray\'}">{{history.date}}</th>' +
        '</tr>' +
        '</table>' +
        '</div>' +
        '</div>',
    created: function () {
        APIhistory.get().then(result =>
            result.json().then(data => data.forEach(historyRow => this.historyArr.push(historyRow))))
    }
});

var app = new Vue({
    el: '#app',
    data: {
        valutes: [],
        historyArr: [],
        selected1: 'selected1',
        selected2: 'selected2',
        resoult: 'JOPA'
    },
    methods: {
        input: function () {
            valuteAPI.get().then(resoult => {
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