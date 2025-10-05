import random
import string
import time
import mysql.connector

# -------------------------------
# 1️⃣ DB 연결 설정
# -------------------------------
config = {
    "host": "localhost",
    "user": "root",
    "password": "hello_debezium!",
    "database": "public",
    "autocommit": True,
}

conn = mysql.connector.connect(**config)
cursor = conn.cursor()

# -------------------------------
# 2️⃣ 헬퍼 함수들
# -------------------------------

def random_name(length=6):
    return ''.join(random.choices(string.ascii_letters, k=length))

def random_money():
    return random.randint(0, 10000)

def user_exists(user_id):
    cursor.execute("SELECT 1 FROM user WHERE id=%s", (user_id,))
    return cursor.fetchone() is not None

def create_user(user_id):
    name = random_name()
    money = random_money()
    try:
        cursor.execute(
            "INSERT INTO user (id, name, money) VALUES (%s, %s, %s)",
            (user_id, name, money)
        )
        print(f"[CREATE] id={user_id}, name={name}, money={money}")
    except mysql.connector.Error as err:
        print(f"[CREATE ERROR] id={user_id}: {err.msg}")

def update_user(user_id):
    # allow upsert!
    # if not user_exists(user_id):
    #     print(f"[SKIP UPDATE] id={user_id} not found.")
    #     return
    money = random_money()
    cursor.execute(
        "UPDATE user SET money=%s WHERE id=%s",
        (money, user_id)
    )
    print(f"[UPDATE] id={user_id}, money={money}")

def delete_user(user_id):
    if not user_exists(user_id):
        print(f"[SKIP DELETE] id={user_id} not found.")
        return
    cursor.execute("DELETE FROM user WHERE id=%s", (user_id,))
    print(f"[DELETE] id={user_id}")

# -------------------------------
# 3️⃣ 메인 루프
# -------------------------------

def run_cdc_simulation(iterations=1000, delay=0.5):
    for i in range(iterations):
        user_id = random.randint(1, 100)
        action = random.choice(["CREATE", "UPDATE", "DELETE"])

        if action == "CREATE":
            create_user(user_id)
        elif action == "UPDATE":
            update_user(user_id)
        elif action == "DELETE":
            delete_user(user_id)

        time.sleep(delay)

# -------------------------------
# 4️⃣ 실행
# -------------------------------
if __name__ == "__main__":
    try:
        run_cdc_simulation(iterations=200, delay=0.3)
    finally:
        cursor.close()
        conn.close()
