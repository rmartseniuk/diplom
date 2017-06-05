var global = {
    mobileClient: false,
    savePermit: true,
    uah: 0,
    eur: 0
};

//VARIABLES
var TOKEN_KEY = "jwtToken"

//FUNCTIONS
function getJwtToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function setJwtToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
}

function removeJwtToken() {
    localStorage.removeItem(TOKEN_KEY);
}

function doLogin(loginData) {

	var success = false;

	$.ajax({
        url: "/auth",
        type: "post",
        data: JSON.stringify(loginData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
		success: function (data) {
            setJwtToken(data.token);
			success = true;
		},
		error: function (jqXHR) {
            if (jqXHR.status == 400) {
                alert("Sorry, not correct request.");
            } else {
                alert(jqXHR.status);
            }
		}
	});

	return success;
}

function createAuthorizationTokenHeader() {
    var token = getJwtToken();
    if (token) {
        return {"Authorization": token};
    } else {
        return {};
    }
}

/**
 * Current account
 */

function getCurrentAccount() {

	var account = null;
	var auth = createAuthorizationTokenHeader();

    $.ajax({
        url: "/current",
        type: "GET",
        datatype: "json",
        async: false,
        headers: createAuthorizationTokenHeader(),
        success: function (data) {
            account = data;
        }
    });

    return account;
}

$(window).load(function(){
    $.getJSON("https://openexchangerates.org/api/latest.json?app_id=1d096e3048814245a619a8a843078c33&base=USD", function(data) {
        global.eur = 1 / data.rates.EUR;
        global.uah = 1 / data.rates.UAH;
    });

	var account = getCurrentAccount();

	if (account) {
		showGreetingPage(account);
	} else {
		showLoginForm();
	}
});

function showGreetingPage(account) {
    initAccount(account);
	var userAvatar = $("<img />").attr("src","images/userpic.jpg");
	$(userAvatar).load(function() {
		setTimeout(initGreetingPage, 500);
	});
}

function showLoginForm() {
	$("#loginpage").show();
	$("#frontloginform").focus();
	setTimeout(initialShaking, 700);
}

