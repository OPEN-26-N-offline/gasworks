import requests
from requests.exceptions import Timeout, HTTPError, RequestException

API_DOMAIN = "http://localhost:8080";

def get_customer_list():
    url = API_DOMAIN + "/customers"
    return fetch_json(url);

def get_meterreading_list():
    url = API_DOMAIN + "/meter-readings"
    return fetch_json(url)

def get_user_list():
    url = API_DOMAIN + "/users"
    return fetch_json(url)

def get_customer_post_base_url():
    return API_DOMAIN + "/customers"

def get_user_post_base_url():
    return API_DOMAIN + "/users"

def get_meterreading_post_base_url():
    return API_DOMAIN + "/meter-readings"



def fetch_json(url):
    try:
        response = requests.get(url, timeout=10.0)

        response.raise_for_status()

        data = response.json()
        return data

    except Timeout:
        print("タイムアウトエラー")
    except HTTPError as e:
        print(f"HTTP Error : status code {e.response.status_code}")
    except RequestException as e:
        print(f"ネットワークエラー: {e}")