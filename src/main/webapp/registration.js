Ext.require([
    'Ext.form.*',
    'Ext.Img',
    'Ext.tip.QuickTipManager',
	'Ext.data.*',
    'Ext.grid.*'
]);
var userName = "";
Ext.onReady(function() {
	new Ext.form.FormPanel({ // инициализация панели с формой.
		bodyStyle:'padding: 10px',
		width:400,
		renderTo: Ext.getBody(), // результат поместить в контейнер с ID='ex0'
		id: 'login-form',   // ID панели. Он понадобится для сабмита формы
		bodyStyle: 'padding:15px;',
		url:'usersOnline.jsp', // url по которому будет оправлена форма
		items: [            // массив полей формы
				{   xtype: 'textfield',
					fieldLabel: 'Login',
					name: 'login',          // имя поля <input name=«login»
					id: 'fieldLogin'
				}],
		buttons: [{                         // набор кнопок. Здесь она всего одна
					text: 'Login',
					handler: function(){
							Ext.getCmp('login-form')    // По ID адресуемся к панели
							.getForm()              // берем форму
							.submit({               // сабмитим
									method: 'get',
									waitTitle:'Connecting', 
									waitMsg:'Sending data...',
									success:        // устанавливаем обработчик норм. завершения
											function(form, action){Ext.getCmp('login-form').hide();
													userName = Ext.getCmp('fieldLogin').getValue();
													chatForm();
											},
									failure:            // устанавливаем обработчик ошибки
											function(form, action){ Ext.Msg.alert("something wrong");
											}
									});
						}
					}]
	});
});
