export class Admin {
  adminId: number;
  adminName: string;
  email: string;
  password: string;

  constructor(
    adminId?: number,
    adminName?: string,
    email?: string,
    password?: string
  ) {
    this.adminId = adminId;
    this.adminName = adminName;
    this.password = password;
    this.email = email;
  }
}
