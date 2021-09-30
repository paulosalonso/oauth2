const config = {
  authorizationServerUrl: "http://localhost:8081",
  resourseServerUrl: "http://localhost:8080",
  callbackUrl: "http://localhost:8000"
};

let accessToken;

function login() {
  let clientId = $("#clientId").val();
  let state = btoa(Math.random());
  localStorage.setItem("clientState", state);

  window.location.href = `${config.authorizationServerUrl}/oauth/authorize?response_type=code&client_id=${clientId}&state=${state}&redirect_uri=${config.callbackUrl}`;
}

function logout() {
  window.location.href = `${config.authorizationServerUrl}/logout`;
}

function gerarAccessToken(code) {
  console("Gerando access token com code <strong>" + code + "</strong>");

  let clientId = $("#clientId").val();
  let clientSecret = $("#clientSecret").val();

  let clientAuth = btoa(clientId + ":" + clientSecret);
  
  let params = new URLSearchParams();
  params.append("grant_type", "authorization_code");
  params.append("code", code);
  params.append("redirect_uri", config.callbackUrl);

  $.ajax({
    url: `${config.authorizationServerUrl}/oauth/token`,
    type: "post",
    data: params.toString(),
    contentType: "application/x-www-form-urlencoded",

    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "Basic " + clientAuth);
    },

    success: function(response) {
      accessToken = response.access_token;
      $("#btn-login").addClass("hide");
      $("#btn-logout").removeClass("hide");
      $("#btn-check-token").removeClass("hide");

      console("Access token gerado: <strong>" + accessToken + "</strong>");
    },

    error: function(error) {
      console("Erro ao gerar access key. Status code: <strong>" + getStatusDescription(error.status) + "</strong>");
    }
  });
}

function verificarToken() {
  console(`Verificando token: <strong>${accessToken}</strong>`);

  $.ajax({
    url: `${config.authorizationServerUrl}/oauth/check_token`,
    type: "post",
    data: {
      token: accessToken
    },
    success: function(response) {
      console(formatJSON(response), false);
      console("Dados do token: ");
    },
    error: function(error) {
      console(`Erro ao verificar token. Status code: <strong>${getStatusDescription(error.status)}</strong>`)
    }
  });
}

function cadastrar() {
  console("Cadastrando cidade com access token <strong>" + accessToken + "</strong>");

  let city = {
    nome: $("#cityName").val(),
    estado: $("#state").val()
  };

  $.ajax({
    url: `${config.resourseServerUrl}/cities`,
    type: "post",
    contentType: "application/json",
    dataType: "json",
    data: JSON.stringify(city),

    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "Bearer " + accessToken);
    },

    success: function(response) {
      console(formatJSON(response), false);
      console("Cidade cadastrada com sucesso: ");
    },

    error: function(error) {
      console("Erro ao cadastrar cidade. Status code: <strong>" + getStatusDescription(error.status) + "</strong>");
    }
  });
}

function consultar() {
  console("Consultando recurso com access token <strong>" + accessToken + "</strong>");

  $.ajax({
    url: `${config.resourseServerUrl}/cities`,
    type: "get",

    beforeSend: function(request) {
      request.setRequestHeader("Authorization", "Bearer " + accessToken);
    },

    success: function(response) {
      console(formatJSON(response), false);
      console("Cidades cadastradas: ");
    },

    error: function(error) {
      console("Erro ao consultar recurso. Status code: <strong>" + getStatusDescription(error.status) + "</strong>");
    }
  });
}

function formatJSON(json) {
  return "<pre>" + JSON.stringify(json, undefined, 4) + "</pre>";
}

function console(text, includeTimestamp = true) {
  let msg = "<p>";

  if (includeTimestamp) {
    msg += new Date().toLocaleString("pt-BR") + " - ";
  }

  msg += text + "</p>";

  $("#console").prepend(msg);
}

function getStatusDescription(statusCode) {
  switch(statusCode) {
    case 400: return `${statusCode} - Bad Request`;
    case 401: return `${statusCode} - Unauthorized`;
    case 403: return `${statusCode} - Forbidden`;
    case 500: return `${statusCode} - Internal Server Error`;
    default: return statusCode;
  }
}

$(document).ready(function() {
  let params = new URLSearchParams(window.location.search);

  let code = params.get("code");
  let state = params.get("state");
  let currentState = localStorage.getItem("clientState");

  if (code) {
    if (currentState == state) {
      gerarAccessToken(code);
    } else {
      console("State inválido");
    }
  }

  if (accessToken) {
    $("#btn-login").addClass("hide");
    $("#btn-logout").removeClass("hide");
    $("#btn-check-token").removeClass("hide");
  } else {
    $("#btn-login").removeClass("hide");
    $("#btn-logout").addClass("hide");
    $("#btn-check-token").addClass("hide");
  }
});

$("#btn-login").click(login);
$("#btn-logout").click(logout);
$("#btn-check-token").click(verificarToken);
$("#btn-cadastrar").click(cadastrar);
$("#btn-consultar").click(consultar);
