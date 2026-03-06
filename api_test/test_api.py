import requests
import json

# 设置基础路径（根据你 Spring Boot 的启动端口修改）
BASE_URL = "http://localhost:8080/student"

def test_get_all():
    print("--- 正在测试：查询所有学生 ---")
    response = requests.get(f"{BASE_URL}/all")
    print(f"状态码: {response.status_code}")
    # 使用 .json() 直接将返回的 Result 转换成字典
    print(f"返回数据: {json.dumps(response.json(), indent=4, ensure_ascii=False)}")

def test_add_student():
    print("\n--- 正在测试：新增学生 ---")
    # 模拟前端发送的 JSON 数据
    payload = {
        "name": "小明",
        "age": 20,
        "gender": 1
    }
    # 指定 headers 为 JSON 格式
    headers = {'Content-Type': 'application/json'}

    response = requests.post(f"{BASE_URL}/add", data=json.dumps(payload), headers=headers)
    print(f"状态码: {response.status_code}")
    print(f"结果: {response.json()}")

if __name__ == "__main__":
    # 执行测试
    try:
        test_get_all()
        test_add_student()
    except Exception as e:
        print(f"请求失败，请检查后端是否启动：{e}")