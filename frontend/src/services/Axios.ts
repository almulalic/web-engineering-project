import axios from "axios";
import { BASE_URL, AUTH_BASE_URL } from "../constants";

export const authorizedAxiosApp = axios.create({
	baseURL: BASE_URL,
	timeout: 10000,
	validateStatus: () => true,
});

export const publicAxiosApp = axios.create({
	baseURL: BASE_URL,
	timeout: 10000,
	validateStatus: () => true,
});

export const axiosAuth = axios.create({
	baseURL: AUTH_BASE_URL,
	timeout: 10000,
	validateStatus: () => true,
});

authorizedAxiosApp.interceptors.request.use(async (config) => {
	const userToken = localStorage.getItem("userToken");

	if (userToken) {
		const token = `Bearer ${userToken}`;
		config.headers!.Authorization = token;
	}
	return config;
});
