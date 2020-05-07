import { UserTypeEnum } from './user-type-enum.enum';

export class Professional {

    userId: number;
    password: string;
    firstName: string
    lastName: string
    address: string;
    email: string;
    gender: any;
    contactNumber: number;
    profilePicture: string;
    previousWorkExperiences: string[];
    skills: string[];
    qualifications: string;
    dateJoined: any;
    dateOfBirth: any;
    userTypeEnum?: UserTypeEnum;

    constructor(
        userId?: number,
        password?: string,
        firstName?: string,
        lastName?: string,
        address?: string,
        email?: string,
        gender?: any,
        contactNumber?: number,
        dateJoined?: any,
        dateOfBirth?: any,
        userTypeEnum?: UserTypeEnum
    ) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.dateJoined = dateJoined;
        this.dateOfBirth = dateOfBirth;
        this.userTypeEnum = userTypeEnum;
    }
}