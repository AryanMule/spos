import tkinter as tk
from tkinter import messagebox

# Function to handle login
def login():
    username = entry_username.get()
    password = entry_password.get()
    
    if username == "admin" and password == "password123":
        messagebox.showinfo("Login Success", "Welcome!")
    else:
        messagebox.showerror("Login Failed", "Invalid username or password")

# Creating the main window
root = tk.Tk()
root.title("Login Window")

# Username Label and Entry
label_username = tk.Label(root, text="Username:")
label_username.grid(row=0, column=0, padx=10, pady=5)
entry_username = tk.Entry(root)
entry_username.grid(row=0, column=1, padx=10, pady=5)

# Password Label and Entry
label_password = tk.Label(root, text="Password:")
label_password.grid(row=1, column=0, padx=10, pady=5)
entry_password = tk.Entry(root, show="*")
entry_password.grid(row=1, column=1, padx=10, pady=5)

# Login Button
login_button = tk.Button(root, text="Login", command=login)
login_button.grid(row=2, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
