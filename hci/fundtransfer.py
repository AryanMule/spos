import tkinter as tk
from tkinter import messagebox

# Function to process the fund transfer
def transfer_funds():
    sender = entry_sender.get()
    receiver = entry_receiver.get()
    amount = entry_amount.get()

    if sender and receiver and amount:
        try:
            amount = float(amount)
            if amount <= 0:
                raise ValueError("Amount must be greater than zero.")
            messagebox.showinfo("Transaction Success", f"Successfully transferred ${amount:.2f} from {sender} to {receiver}.")
        except ValueError as e:
            messagebox.showerror("Input Error", str(e))
    else:
        messagebox.showwarning("Input Error", "All fields are required.")

# Creating the main window
root = tk.Tk()
root.title("Fund Transfer")

# Sender Label and Entry
label_sender = tk.Label(root, text="Sender's Name:")
label_sender.grid(row=0, column=0, padx=10, pady=5)
entry_sender = tk.Entry(root)
entry_sender.grid(row=0, column=1, padx=10, pady=5)

# Receiver Label and Entry
label_receiver = tk.Label(root, text="Receiver's Name:")
label_receiver.grid(row=1, column=0, padx=10, pady=5)
entry_receiver = tk.Entry(root)
entry_receiver.grid(row=1, column=1, padx=10, pady=5)

# Amount Label and Entry
label_amount = tk.Label(root, text="Amount ($):")
label_amount.grid(row=2, column=0, padx=10, pady=5)
entry_amount = tk.Entry(root)
entry_amount.grid(row=2, column=1, padx=10, pady=5)

# Transfer Button
transfer_button = tk.Button(root, text="Transfer", command=transfer_funds)
transfer_button.grid(row=3, columnspan=2, pady=10)

# Run the main loop
root.mainloop()
