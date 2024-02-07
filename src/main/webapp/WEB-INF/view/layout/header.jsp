<%@page import="com.mysql.cj.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>My Bank</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="/assets/vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="/assets/vendor/font-awesome/css/font-awesome.min.css" />
<link rel="stylesheet" href="/assets/vendor/linearicons/style.css" />
<link rel="stylesheet"
	href="/assets/vendor/chartist/css/chartist-custom.css" />
<!-- MAIN CSS -->
<link rel="stylesheet" href="/assets/css/main.css" />
<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
<link rel="stylesheet" href="/assets/css/demo.css" />
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet" />
<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="/assets/img/apple-icon.png" />
<link rel="icon" type="image/png" sizes="96x96"
	href="/assets/img/favicon.png" />
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="/"> My Bank </a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn navbar-btn-right">
					<a class="btn btn-success update-pro"
						href="https://github.com/StarsinLiver/tenco.git"
						title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i>
						<span>해당 깃 주소</span></a>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<c:choose>
							<c:when test="${principal != null}">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><i class="lnr lnr-question-circle"></i>
										<span>Help</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
									<ul class="dropdown-menu">

										<li><a href="/account/save" class="active"><i
												class="lnr lnr-home"></i> <span>계좌 생성</span></a></li>
										<li><a href="/account/list" class=""><i
												class="lnr lnr-code"></i> <span>계좌 목록</span></a></li>
										<li><a href="/account/withdraw" class=""><i
												class="lnr lnr-chart-bars"></i> <span>출금</span></a></li>
										<li><a href="/account/deposit" class=""><i
												class="lnr lnr-cog"></i> <span>입금</span></a></li>
										<li><a href="/account/transfer" class=""><i
												class="lnr lnr-cog"></i> <span>이체</span></a></li>
									</ul></li>
							</c:when>
						</c:choose>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span>회원 정보</span> <i
								class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<c:choose>
									<c:when test="${principal != null}">
										<li><a href="/user/logout" class=""><i
												class="lnr lnr-alarm"></i> <span>로그아웃</span></a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/user/sign-in" class=""><i
												class="lnr lnr-dice"></i> <span>로그인</span></a></li>
										<li><a href="/user/sign-up" class=""><i
												class="lnr lnr-text-format"></i> <span>회원가입</span></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<div class="text-center">
						<h2 style="color : white">환영합니다.</h2>
						<!-- 로그인 여부에 코드 추가하기 -->
						<c:choose>
							<c:when test="${principal != null }">
								<h5 class="mt-5" style="color : white">이름 : ${principal.username}계정</h3>
								<img style="width : 200px" src="${principal.setupUserImage()}"
									alt="" />
							</c:when>
							<c:otherwise>
								<div class="m--profile"></div>
							</c:otherwise>
						</c:choose>
						<hr class="d-sm-none">
					</div>
					<ul class="nav">
						<c:choose>
							<c:when test="${principal != null}">
								<li><a href="/user/logout" class=""><i
										class="lnr lnr-alarm"></i> <span>로그아웃</span></a></li>
								<li><a href="/account/save" class="active"><i
										class="lnr lnr-home"></i> <span>계좌 생성</span></a></li>
								<li><a href="/account/list" class=""><i
										class="lnr lnr-code"></i> <span>계좌 목록</span></a></li>
								<li><a href="/account/withdraw" class=""><i
										class="lnr lnr-chart-bars"></i> <span>출금</span></a></li>
								<li><a href="/account/deposit" class=""><i
										class="lnr lnr-cog"></i> <span>입금</span></a></li>
								<li><a href="/account/transfer" class=""><i
										class="lnr lnr-cog"></i> <span>이체</span></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/user/sign-in" class=""><i
										class="lnr lnr-dice"></i> <span>로그인</span></a></li>
								<li><a href="/user/sign-up" class=""><i
										class="lnr lnr-text-format"></i> <span>회원가입</span></a></li>
							</c:otherwise>
						</c:choose>

					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->